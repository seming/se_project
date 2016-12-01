package com.example.dailyassistant;

import java.util.ArrayList;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.LauncherActivity.ListItem;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

@SuppressWarnings("deprecation")
public class NoteManager extends ActionBarActivity {
	private ActionBar actionbar;
	private Button buttonAddNote;
	private Button buttonDeleteNote;
	private Button buttonViewContents;
	private ListView listviewViewAllList;
	private ArrayAdapter adapterForListView;
	private ArrayList<ListItem> arraylistForListView;
	private final int ADD_NOTE_REQUEST = 111;
	private final int DELETE_NOTE_REQUEST = 222;
	private final int VIEW_CONTENTS_REQUEST = 333;
	
	//private DatabaseAdapter databaseAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		createBackButton();
		setContentView(R.layout.activity_note_manager);
				
		buttonAddNote = (Button)findViewById(R.id.button_add_note);
		buttonDeleteNote = (Button)findViewById(R.id.button_delete_note);
		buttonViewContents = (Button)findViewById(R.id.button_view_contents);
		listviewViewAllList = (ListView)findViewById(R.id.listview_view_all_list);
		listviewViewAllList.setAdapter(adapterForListView);
		
		//databaseAdapter = new DatabaseAdapter();
		//databaseAdapter.open("note의 database파일 경로");
		//arraylistForListView = databaseAdapter.getDataList();
		
	}

	public void createBackButton() {
		
		actionbar = getSupportActionBar();
		actionbar.setTitle("Note Manager");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		//실행위해서 xml에 mata-data 추가해야 함.
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.note_manager, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	Button.OnClickListener mClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View buttonview) {
			int caseId = buttonview.getId();
			Class newActivityClass = null;
			switch(caseId){
			case R.id.button_add_note:
				newActivityClass = NewNoteCreator.class;
				break;
			case R.id.button_delete_note:
				newActivityClass = NoteDelector.class;
				break;
			case R.id.button_view_contents:
				newActivityClass = NoteViewer.class;
			}
			openNewActivity(caseId,newActivityClass);
		}
	};

	
	public void openNewActivity(int caseId, Class activityClass){
		Intent intent = new Intent(getApplicationContext(),activityClass);
		startActivityForResult(intent,caseId);
	}

	
	public void onActivityResult(int requestCode, int resultCode,Intent intent){
		//super.onActivityResult(requestCode, resultCode, intent);
		
		switch(requestCode){
		case ADD_NOTE_REQUEST:
			if(resultCode==RESULT_OK){
				addNote();
			}
			break;
		case DELETE_NOTE_REQUEST:
			if(resultCode==RESULT_OK){
				deleteNote();
			}
			break;
		case VIEW_CONTENTS_REQUEST:
			if(resultCode==RESULT_OK){
				//must be filled
			}
			
		}
	}

	public void deleteNote() {
		// TODO Auto-generated method stub
		
	}

	public void addNote() {
		// TODO Auto-generated method stub
		
	}
	
}
