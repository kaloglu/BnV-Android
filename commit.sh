#!/bin/bash
if [[ "$TRAVIS_BRANCH" = "master" && "$TRAVIS_PULL_REQUEST" = "false" ]]; then
    set -e
    
    git config --global user.email "build@travis-ci.com"
    git config --global user.name "Travis CI"
    git remote add release "https://$GH_TOKEN@github.com/kaloglu/BnV-Android.git"
    git checkout $TRAVIS_BRANCH

    file="./app/version.properties"

    if [[ -f "$file" ]]; then
        while IFS='=' read -r key value
        do
            key=$(echo ${key} | tr '.' '_')
            eval ${key}=\$value
        done < "$file"

        echo "major       = " ${VERSION_MAJOR}
        echo "minor       = " ${VERSION_MINOR}
        echo "patch       = " ${VERSION_PATCH}
    else
        echo "$file not found."
    fi

    version=${VERSION_MAJOR}.${VERSION_MINOR}.${VERSION_PATCH}

    git log $(git describe --tags --abbrev=0)..HEAD --oneline > beta_release_notes.txt
    ./gradlew postBeta
    ./gradlew clean assembleClientBeta crashlyticsUploadDistributionClientBeta
    git add . -u
    git commit -m "[ci skip] ${version}"
    git push -f release $TRAVIS_BRANCH 2>&1
    git tag beta/${version} -a -m "[ci tagging] beta/${version}"
    git push -f release $TRAVIS_BRANCH --tags 2>&1
fi
