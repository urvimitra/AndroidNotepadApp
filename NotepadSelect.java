public class NotePadSelect extends AppCompatActivity
{
    private List & lt;
    NotesBuilder & gt;
    notesList = new ArrayList & lt; & gt;
    ();
    private NotesAdapter nAdapter;
    private RecyclerView notesRecycler;
    private List & lt;
    NotesBuilder & gt;
    notesList = new ArrayList & lt; & gt;
    ();
    private NotesAdapter nAdapter;
    private RecyclerView notesRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        notesRecycler = (RecyclerView) findViewById(R.id.notes);
        nAdapter = new NotesAdapter(notesList);

        REcyclerView.layourmanager lm=new LinearLayoutManager(getApplicationContext());
        notesRecycler.setLayoutManager(lm);
        notesRecycler.setItemAnimator(new DefaultItemAnimator());
        notesRecycler.setAdapter(nAdapter);
        prepareNotes();
    }
    private void prepareNotes()
    {
        File dir;
        dir=getFilesDir();
        File[] files=dir.listFiles();
        String theFile;
        for(int f=1;f&lt;=files.length();f++)
        {
            theFile="Note"+f+".txt";
            NotesBuilder note=new NotesBuilder(theFile,Open(theFile));
            notesList.add(note);
        }
    }
    public String Open(String fileName) {
        String content = "";
        try {
            InputStream in = openFileInput(fileName);
            if ( in != null) {
                InputStreamReader tmp = new InputStreamReader( in );
                BufferedReader reader = new BufferedReader(tmp);
                String str;
                StringBuilder buf = new StringBuilder();
                while ((str = reader.readLine()) != null) {
                    buf.append(str + "\n");
                } in .close();

                content = buf.toString();
            }
        } catch (java.io.FileNotFoundException e) {} catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }

        return content;
    }
}