package com.PP.Matcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class profileArrayAdaptor extends ArrayAdapter<profileCard> {

	Context context;

	public profileArrayAdaptor(Context context, int resourceID, List<profileCard> profiles){
		super(context, resourceID, profiles);
	}

	public View getView(int pos, View convertView, ViewGroup parent) {
		profileCard profile = getItem(pos);

		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.profile, parent, false);
		}

		TextView vFirstName = convertView.findViewById(R.id.profile_firstName);
		TextView vAverage = convertView.findViewById(R.id.profile_average);
		TextView vFaculty = convertView.findViewById(R.id.profile_faculty);
		TextView vLastName = convertView.findViewById(R.id.profile_lastName);
		TextView vPrompt1 = convertView.findViewById(R.id.profile_prompt1);
		TextView vPrompt2 = convertView.findViewById(R.id.profile_prompt2);
		TextView vPrompt3 = convertView.findViewById(R.id.profile_prompt3);
		TextView vResponse1 = convertView.findViewById(R.id.profile_response1);
		TextView vResponse2 = convertView.findViewById(R.id.profile_response2);
		TextView vResponse3 = convertView.findViewById(R.id.profile_response3);
		TextView vYear = convertView.findViewById(R.id.profile_year);

		vFirstName.setText(profile.getFirstName());
		vLastName.setText(profile.getLastName());
		vAverage.setText(String.valueOf(profile.getAverage()));
		vFaculty.setText(profile.getFaculty());
		vPrompt1.setText(profile.getPrompt1());
		vPrompt2.setText(profile.getPrompt2());
		vPrompt3.setText(profile.getPrompt3());
		vResponse1.setText(profile.getResponse1());
		vResponse2.setText(profile.getResponse2());
		vResponse3.setText(profile.getResponse3());
		vYear.setText(String.valueOf(profile.getYear()));

		return convertView;
	}



}