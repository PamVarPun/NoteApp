package org.classapp.noteapp.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import org.classapp.noteapp.Models.Note
import org.classapp.noteapp.R
import kotlin.random.Random

class NotesAdapter (private val context: Context, val listener : NotesClickListener) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>(){
/* สร้าง Notelist ขึ้นมา เพื่อการแสดงผลผ่านหน้าจอด้วย recycleview  ส่วน fulllist เป็น list ที่เราจะดึงขึ้นมาจาก database ที่มีข้อมูลเก่าบันทึก*/
    /*ฉะนั้น เราจะมาจัดการกำหนดแค่ในสว่นของ NoteList เพียงอย่างเดียวเท่านั้น*/
    private val NotesList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item, parent,false)
        /*ดึง layout listitem ขึ้นมาแสดง*/
        )
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = NotesList[position]
        holder.title.text = currentNote.title
        holder.title.isSelected = true

        holder.note_tv.text = currentNote.note
        holder.date.text = currentNote.date
        holder.date.isSelected = true

        holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(), null))

        holder.notes_layout.setOnClickListener{

            listener.onItemClicked(NotesList[holder.adapterPosition])
        }

        holder.notes_layout.setOnLongClickListener{
            listener.onLongItemClicked(NotesList[holder.adapterPosition], holder.notes_layout)
            true
        }
    }

    override fun getItemCount(): Int {
        return  NotesList.size
    }

    fun updateList(newList : List<Note>){
    /*fulllist คือถังเก็บของ Database ถ้าไม่มีของก็ว่าง ๆ แต่ถ้ามีก็ add เข้า Note ที่เราสร้าง Data Class ขึ้นมา
    * ส่วน Notelist ที่เราสร้างคือหน้า Note Interface ที่เราเห็น ขึ้นต้องเอาไปบันทึกไว้ใน fullist เป็นฐานข้อมูลไว้*/
        fullList.clear()
        fullList.addAll(newList)

        NotesList.clear()
        NotesList.addAll(fullList)

        notifyDataSetChanged()

    }

    fun filterList(search : String){

        NotesList.clear()

        for (item in fullList){

            if(item.title?.lowercase()?.contains(search.lowercase())== true ||
                    item.note?.lowercase()?.contains(search.lowercase()) == true){

                NotesList.add(item)
            }
        }
        notifyDataSetChanged()
    }

    /*สร้างสีพื้นของ note ให้เป็น random ขึ้นมา*/
    fun randomColor() : Int{
        val list = ArrayList<Int>()
        list.add(R.color.NoteColor1)
        list.add(R.color.NoteColor2)
        list.add(R.color.NoteColor3)
        list.add(R.color.NoteColor4)
        list.add(R.color.NoteColor5)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        /*ดึง xml มาแสดงผล*/
        val notes_layout = itemView.findViewById<CardView>(R.id.card_layout)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val note_tv = itemView.findViewById<TextView>(R.id.tv_note)
        val date = itemView.findViewById<TextView>(R.id.tv_date)

    }

    interface NotesClickListener{
        fun onItemClicked(note: Note)
        fun onLongItemClicked(note: Note, cardView: CardView)


    }

}