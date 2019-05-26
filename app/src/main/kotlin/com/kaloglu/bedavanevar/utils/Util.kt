package com.kaloglu.bedavanevar.utils

import android.content.Context
import android.graphics.Bitmap
import android.media.ExifInterface
import android.net.Uri
import android.os.Environment
import android.text.TextUtils
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * Created by dcanbatman on 09/05/2017.
 */

class Util {


    @Throws(IOException::class)
    private fun getRealPathFromURI19(context: Context, uri: Uri): String? {
        val fileName = getName(uri.path)
        if (!TextUtils.isEmpty(fileName)) {
            val copyFile = File(context.externalCacheDir!!.toString() + File.separator + fileName)
            copy(context, uri, copyFile)
            return copyFile.absolutePath
        }
        return null
    }

    //    public static void copy(Context context, Uri srcUri, File dstFile) {
    //        try {
    //            InputStream inputStream = context.getContentResolver().openInputStream(srcUri);
    //            if (inputStream == null) return;
    //            OutputStream outputStream = new FileOutputStream(dstFile);
    //            IOUtils.copy(inputStream, outputStream);
    //            inputStream.close();
    //            outputStream.close();
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        }
    //    }

    private fun getName(filename: String?): String? {
        if (filename == null) {
            return null
        }
        val index = filename.lastIndexOf('/')
        return filename.substring(index + 1)
    }

    companion object {

        fun getCameraPhotoOrientation(imagePath: String): Int {
            var rotate = 0
            try {
                var exif: ExifInterface? = null
                try {
                    exif = ExifInterface(imagePath)
                } catch (e1: IOException) {
                    e1.printStackTrace()
                }

                val orientation = exif!!.getAttributeInt(
                        ExifInterface.TAG_ORIENTATION, 0)
                when (orientation) {

                    ExifInterface.ORIENTATION_ROTATE_270 -> rotate = 270
                    ExifInterface.ORIENTATION_ROTATE_180 -> rotate = 180
                    ExifInterface.ORIENTATION_ROTATE_90 -> rotate = 90
                    else -> rotate = 0
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return rotate
        }

        fun createFileFromBmp(bmp: Bitmap): File? {

            val TEMP_DIR_PATH = Environment.getExternalStorageDirectory().path + "/delta/images/"
            val dir = File(TEMP_DIR_PATH)
            if (!dir.exists()) {
                dir.mkdirs()
            }

            val fileName = "signature.png"
            if (!TextUtils.isEmpty(fileName)) {
                val copyFile = File(TEMP_DIR_PATH + File.separator + fileName)
                var out: FileOutputStream? = null
                try {
                    out = FileOutputStream(copyFile)
                    bmp.compress(Bitmap.CompressFormat.PNG, 85, out) // bmp is your Bitmap instance
                    // PNG is a lossless format, the compression factor (100) is ignored

                    return copyFile
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    try {
                        out?.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
            }

            return null
        }

        fun normalizeCharacters(word: String): String {
            var message = word
            val oldValue = charArrayOf('ö', 'Ö', 'ü', 'Ü', 'ç', 'Ç', 'İ', 'ı', 'Ğ', 'ğ', 'Ş', 'ş')
            val newValue = charArrayOf('o', 'O', 'u', 'U', 'c', 'C', 'I', 'i', 'G', 'g', 'S', 's')
            for (i in oldValue.indices) {
                message = message.replace(oldValue[i], newValue[i])
            }
            return message
        }

        fun getFilePathFromURI(context: Context, contentUri: Uri): String? {
            //copy file and send new file path
            try {
                val TEMP_DIR_PATH = Environment.getExternalStorageDirectory().path + "/delta/images/"
                val dir = File(TEMP_DIR_PATH)
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                val fileName = getFileName(contentUri)
                if (!TextUtils.isEmpty(fileName)) {
                    val copyFile = File(TEMP_DIR_PATH + File.separator + fileName)
                    copy(context, contentUri, copyFile)
                    return copyFile.absolutePath
                }
            } catch (e: Exception) {
                return null
            }

            return null
        }

        fun getFileName(uri: Uri?): String? {
            if (uri == null) return null
            var fileName: String? = null
            val path = uri.path
            val cut = path!!.lastIndexOf('/')
            if (cut != -1) {
                fileName = path.substring(cut + 1)
            }
            return fileName!! + ".jpg"
        }

        @Throws(IOException::class)
        private fun copy(context: Context, srcUri: Uri, dstFile: File) {
            val inputStream = context.contentResolver.openInputStream(srcUri) ?: return

            val outputStream = FileOutputStream(dstFile)
            val bArr = ByteArray(1024)
            while (true) {
                val read = inputStream.read(bArr)
                if (read == -1) {
                    break
                }
                outputStream.write(bArr, 0, read)
                outputStream.flush()
            }
            inputStream.close()
            outputStream.close()
        }

    }
}
