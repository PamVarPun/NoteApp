package org.classapp.noteapp.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/* สร้าง Data Class ขึ้นมา เพื่อเก็บข้อมูลในการบันทึก Note */
/*@Entity เราจะใช้ใน room DB ซึ่งเป็น library ที่เอาไว้บันทึกและ retrieve */


@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true) val id : Int?,
    @ColumnInfo(name = "title") val title : String?,
    @ColumnInfo(name = "note") val note : String?,
    @ColumnInfo(name = "date") val date : String?
)  : java.io.Serializable
/*Serializability of a class is enabled by the class implementing the java.io.Serializable interface.*/