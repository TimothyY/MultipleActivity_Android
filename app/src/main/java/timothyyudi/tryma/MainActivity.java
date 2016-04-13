package timothyyudi.tryma;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //deklarasi
    Button btnLogin, btnPickContact;
    EditText et;

    static final int PICK_CONTACT_REQUEST = 1000;  // The request code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisiasi
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        btnPickContact = (Button)findViewById(R.id.btnPick);
        btnPickContact.setOnClickListener(this);

        et = (EditText) findViewById(R.id.etUsername);
        et.setError("Username kurang panjang");
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnLogin){
            Intent objIntent = new Intent(this,SecondActivity.class);
            objIntent.putExtra("username",et.getText().toString());
            objIntent.putExtra("ini key", "ini value");
            startActivity(objIntent);
        }else if(v.getId()==R.id.btnPick){
            pickContact(); //implementasi contoh penggunaan lain intent
            Log.v("this is log v","message here");
        }
    }

    //region contoh penggunaan lain intent
    private void pickContact() {
        Intent pickContactIntent =
                new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        pickContactIntent.setType(
                ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_CONTACT_REQUEST) {
            if (resultCode == RESULT_OK) {}
            else if(resultCode == RESULT_CANCELED){
                Toast.makeText(this, "User Tekan Back", Toast.LENGTH_SHORT).show();
            }
        }
    }
    //endregion
}