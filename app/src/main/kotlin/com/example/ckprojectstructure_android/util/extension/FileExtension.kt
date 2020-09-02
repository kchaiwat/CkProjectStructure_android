package com.example.ckprojectstructure_android.util.extension

import java.io.File


fun File.deleteRecursive() {
    if (this.isDirectory) {
        for (child in this.listFiles()) {
            child.deleteRecursive()
        }
    }

    this.delete()
}