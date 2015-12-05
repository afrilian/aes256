package dlmbg.pckg.aes.enkripsi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EnkripsiActivity extends Activity {
	TextView hasil_enkripsi, hasil_dekripsi, tipe;
	EditText in_plain,in_kunci;
	public String hasil,hasil2;
	public int tipe_enkripsi = 128;
	public String var_in_plain="";
	public String var_in_kunci="";
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.enkripsi);

        hasil_enkripsi = (TextView) findViewById(R.id.txt_enkripsi);
        hasil_dekripsi = (TextView) findViewById(R.id.txt_dekripsi);
        tipe = (TextView) findViewById(R.id.txt_tipe);
        in_plain = (EditText) findViewById(R.id.edit_plain);
        in_kunci = (EditText) findViewById(R.id.edit_kunci);
        
        tipe.setText("Blok Cipher AES-"+tipe_enkripsi+" bit");
        
        Button btn_enkripsi = (Button) findViewById(R.id.btn_enkripsi);
        btn_enkripsi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {	
		        
				try{
			        var_in_plain = in_plain.getText().toString();
			        var_in_kunci = in_kunci.getText().toString();
			        
			        Aes enkripsi = new Aes();
					hasil = enkripsi.encrypt(var_in_kunci,var_in_plain,tipe_enkripsi);
					hasil_enkripsi.setText(hasil);
		        }
		        catch (Exception e) {
				}
			}
		});

        Button btn_dekripsi = (Button) findViewById(R.id.btn_dekripsi);
        btn_dekripsi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {	
		        
				try{
			        Aes enkripsi = new Aes();
					hasil2 = enkripsi.decrypt(var_in_kunci,hasil_enkripsi,tipe_enkripsi);
					hasil_dekripsi.setText(hasil2);
		        }
		        catch (Exception e) {
				}
			}
		});
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.opt_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        	case R.id.tentang:
        		AlertDialog alertDialog;
        		alertDialog = new AlertDialog.Builder(this).create();
        		alertDialog.setTitle("Sekilas Enkripsi AES");
        		alertDialog.setMessage("Dalam kriptografi, Advanced Encryption Standard (AES) merupakan standar enkripsi " +
        				"dengan kunci-simetris yang diadopsi oleh pemerintah Amerika Serikat. Standar ini terdiri atas 3 blok " +
        				"cipher, yaitu AES-128, AES-192 and AES-256, yang diadopsi dari koleksi yang lebih besar yang awalnya " +
        				"diterbitkan sebagai Rijndael.");
        		alertDialog.setButton("#OKOK", new DialogInterface.OnClickListener() {
        		    @Override
        		    public void onClick(DialogInterface dialog, int which) {
        		        dialog.dismiss();
        		    }
        		});
        		alertDialog.show();
        		return true;
        	case R.id.keluar:	
				Intent exit = new Intent(Intent.ACTION_MAIN);
				exit.addCategory(Intent.CATEGORY_HOME); exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				EnkripsiActivity.this.finish();
				startActivity(exit);
        		return true;
        	case R.id.opt1:	
        		tipe_enkripsi = 128;
                tipe.setText("Blok Cipher AES-"+tipe_enkripsi+" bit");
        		return true;
        	case R.id.opt2:	
        		tipe_enkripsi = 192;
                tipe.setText("Blok Cipher AES-"+tipe_enkripsi+" bit");
        		return true;
        	case R.id.opt3:	
        		tipe_enkripsi = 256;
                tipe.setText("Blok Cipher AES-"+tipe_enkripsi+" bit");
        		return true;
        	default:
        		return super.onOptionsItemSelected(item);
        }
    }
}
