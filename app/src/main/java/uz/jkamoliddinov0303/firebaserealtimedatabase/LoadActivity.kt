package uz.jkamoliddinov0303.firebaserealtimedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_load.*

class LoadActivity : AppCompatActivity() {
    private lateinit var adapter: RvAdapter
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userList: MutableList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)
        userList = ArrayList()
        title = "Users"
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot.children
                for (child in children) {
                    val user = child.getValue(User::class.java)
                    if (user != null) {
                        userList.add(user)
                    }
                }
                adapter = RvAdapter(userList as ArrayList<User>)
                recyclerView.layoutManager = LinearLayoutManager(this@LoadActivity)
                recyclerView.adapter = adapter

            }
        })

    }
}