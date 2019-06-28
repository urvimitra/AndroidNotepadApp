package com.example.dpm.notepadapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    EditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save("Note1.txt");
            }
        });
        e=(EditText) findViewById(R.id.EditText1);
        e.setText(Open("Note1.txt"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Intent myIntent = new Intent(MainActivity.this, NoteSelect.class);
        MainActivity.this.startActivity(myIntent);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void Save(String name)
    {
        try{
            OutputStreamWriter out=new OutputStreamWriter(openFileOutput(name,0));
            out.write(e.getText().toString());
            out.close();
            Toast.makeText(this,"Note saved!",Toast.LENGTH_SHORT).show();
        }catch(Throwable t)
        {
            Toast.makeText(this,"Exception:"+t.toString(),Toast.LENGTH_LONG).show();
        }
    }
    public String Open(String name)
    {
        String content="";
        if(FileExists(name))
        {
            try{
                InputStream in=openFileInput(name);
                if(in!=null)
                {
                    InputStreamReader tmp=new InputStreamReader(in);
                    BufferedReader br=new BufferedReader(tmp);
                    String str;
                    StringBuilder buf=new StringBuilder();
                    while((str=br.readLine())!=null)
                    {
                        buf.append(str+"\n");
                    }
                    in.close();
                    content=buf.toString();
                }
            }catch(java.io.FileNotFoundException e){} catch(Throwable t){ Toast.makeText(this,"Exception: "+t.toString(),Toast.LENGTH_LONG).show();}

        }
        return content;
    }
}public boolean FileExists(String name){
    File file=getBaseContext().getFileStreamPath(name);
    return file.exists();
}
