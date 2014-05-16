/*
 * Copyright (C) 2014 Michell Bak
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.miz.mizuu;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import com.miz.base.MizActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.miz.mizuu.fragments.FileBrowserFragment;

public class AddLocalFileSource extends MizActivity {

	private static String TAG = "FileBrowserFragment";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Fragment frag = getSupportFragmentManager().findFragmentByTag(TAG);
		if (frag == null) {
			final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.add(android.R.id.content, FileBrowserFragment.newInstance(
					Environment.getExternalStorageDirectory().getAbsolutePath(),
					getIntent().getExtras().getString("type").equals("movie")
					), TAG);
			ft.commit();
		}	
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public void onBackPressed() {
		FileBrowserFragment frag = (FileBrowserFragment) getSupportFragmentManager().findFragmentByTag(TAG);
		frag.onBackPressed();
	}
}