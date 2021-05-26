package uz.jkamoliddinov0303.firebaserealtimedatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference

    var user_id: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

//        databaseReference.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                user_id = snapshot.childrenCount
//            }
//
//            override fun onCancelled(error: DatabaseError) {}
//        })

        btn_save.setOnClickListener {
            //o'zi automatik unique key berib ketadi ya'ni child ochib ketadi !
//            val key = databaseReference.push().key
//            databaseReference.child(key!!).setValue(user)

            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        user_id = snapshot.childrenCount
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

                
            })
            val user = User(et_username.text.toString(), et_password.text.toString())
            databaseReference.child("user${++user_id}").setValue(user)
            Toast.makeText(applicationContext, "User successfully saved", Toast.LENGTH_SHORT).show()

        }
        navigate_to_load.setOnClickListener {
            startActivity(Intent(this@MainActivity, LoadActivity::class.java))
        }
    }
}