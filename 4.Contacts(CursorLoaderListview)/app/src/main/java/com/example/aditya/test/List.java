package com.example.aditya.test;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

import static android.provider.ContactsContract.CommonDataKinds.Phone.NUMBER;
import static android.provider.OpenableColumns.DISPLAY_NAME;


public class List extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static ArrayList<String> arrayName=new ArrayList<>();
    public static ArrayList<String> arrayContact=new ArrayList<>();
    private char start;
    private char end;
    ListView lv;

    public List() {
    }
    public static List newInstance(char a,char b) {
        List fragment = new List();
        Bundle args = new Bundle();
        args.putChar(ARG_PARAM1, a);
        args.putChar(ARG_PARAM2, b);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            start = getArguments().getChar(ARG_PARAM1);
            end = getArguments().getChar(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_list, container, false);
        getLoaderManager().initLoader(0,null,this);
        lv=v.findViewById(R.id.list);
        return v;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri CONTACT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String selection="";
        while(start!=end) {
            selection += ContactsContract.Contacts.DISPLAY_NAME + " LIKE '" + start + "%' or ";
            ++start;
        }
        selection += ContactsContract.Contacts.DISPLAY_NAME + " LIKE '" + start + "%'";
        return new CursorLoader(getActivity(),CONTACT_URI,null, selection,null,ContactsContract.Contacts.DISPLAY_NAME+" asc");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();
        String[] from={ContactsContract.Contacts.DISPLAY_NAME,NUMBER};
        int[] to={R.id.name,R.id.contact};
        /*//using custom Adapter
        while(data.moveToNext()){
            arrayName.add(data.getString(data.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
            arrayContact.add(data.getString(data.getColumnIndex(NUMBER)));
        }
        //using custom adapter
        lv.setAdapter(new Adapter(getActivity());*/
        CursorAdapter cursorAdapter=new SimpleCursorAdapter(getActivity(),R.layout.single_item,null,from,to,0);
        lv.setAdapter(cursorAdapter);
        cursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        arrayContact.clear();
        arrayName.clear();

    }
}
