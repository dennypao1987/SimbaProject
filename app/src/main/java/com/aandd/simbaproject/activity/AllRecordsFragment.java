package com.aandd.simbaproject.activity;


import com.aandd.simbaproject.media.Play;
import com.aandd.simbaproject.utility.FileUtil;
import com.example.simbaproject.R;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;


public class AllRecordsFragment extends ListFragment implements AdapterView.OnItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_allrecord, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, FileUtil.getFilesName());
        setListAdapter(adapter);
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                onLongItemClick(position);
                return true;
            }
        });
        getListView().setOnItemClickListener(this);
    }

    //esecuzione file audio
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
        Play.mediaPlay(FileUtil.getPath() + "/" + item);
    }

    //eliminazione file e refresh del fragment
    public void onLongItemClick(int position){
        final int pos = position;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setMessage("Eliminare la registrazione?");
        alertDialogBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                FileUtil.deleteFile((String) getListAdapter().getItem(pos));
                onActivityCreated(getArguments());
                MainActivity main = (MainActivity) getActivity();
                main.refreshDraver();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}
