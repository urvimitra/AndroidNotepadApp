public class NotesAdapter extends RecyclerView.Adapter & lt;
NotesAdapter.MyViewHolder & gt{
    private List & lt;
    NotesBuilder & gt;
    noteslist;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,content;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            content = (TextView) view.findViewById(R.id.content);

        }
    }
        public NotesAdapter(List & lt; NotesBuilder & gt; notesList) {
            this.notesList = notesList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
        {
            View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow,parent,false);
            return new MyViewHolder(itemView);

        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, int pos)
        {
            NotesBuilder note=notesList.get(pos);
            holder.title.setText(note.getTitle());
            holder.content.setTExt(note.getContent());
        }
        @Override
        public int getItemCount() {
            return notesList.size();
        }
    }