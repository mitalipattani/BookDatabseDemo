package com.ciccc_cirac.bookdatabsedemo;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    DatabaseHandler db = new DatabaseHandler(this);
    Button addBook;
    EditText booktitle, bookauthor;
    ListView lv_books;
    BookAdapter bookAdapter;
    List<Book> listbooks; //Book is the model class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBook = (Button)findViewById(R.id.button_add);
        booktitle = (EditText)findViewById(R.id.titleEdit);
        bookauthor = (EditText)findViewById(R.id.authorEdit);
        lv_books = (ListView)findViewById(R.id.list_book);
        addBook.setOnClickListener(this);

        //todo 8) Read all books from database and add it into the list.
        listbooks = db.getAllBooks();
        List<Integer> listID = new ArrayList<Integer>();
        List<String> listTitle = new ArrayList<String>();
        List<String> listAuthor = new ArrayList<String>();

        for(int i=0; i<listbooks.size(); i++)
        {
            listID.add(i, listbooks.get(i).getId());
            listTitle.add(i,listbooks.get(i).getTitle());
            listAuthor.add(i,listbooks.get(i).getAuthor());
        }
        bookAdapter = new BookAdapter(this, listID,
                listTitle, listAuthor);
        lv_books.setAdapter(bookAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_add:
                add(v);
                break;
        }
    }

    public void add(View v)
    {
        String title = booktitle.getText().toString();
        String author = bookauthor.getText().toString();
        db.addBook(new Book(title,author));
        Toast.makeText(this, "BOOK ADDED SUCCESFULLY",
                Toast.LENGTH_LONG).show();

    }
}
