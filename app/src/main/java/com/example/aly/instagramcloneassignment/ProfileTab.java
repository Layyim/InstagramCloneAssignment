package com.example.aly.instagramcloneassignment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment
{

    private EditText editProfileName, editProfileBio, editProfileProfession, editProfileHobbies,
                        editProfileFavouriteSports;
    private Button btnProfileUpdate;

    public ProfileTab()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);

        editProfileName = view.findViewById(R.id.editProfileName);
        editProfileBio = view.findViewById(R.id.editProfileBio);
        editProfileProfession = view.findViewById(R.id.editProfileProfession);
        editProfileHobbies = view.findViewById(R.id.editProfileHobbies);
        editProfileFavouriteSports = view.findViewById(R.id.editProfileFavouriteSports);

        btnProfileUpdate = view.findViewById(R.id.btnProfileUpdate);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        if (parseUser.get("profileName") == null)
        {
            editProfileName.setText("");
        }

        else if (parseUser.get("profileBio") == null)
        {
            editProfileBio.setText("");
        }

        else if (parseUser.get("profileProfession") == null)
        {
            editProfileProfession.setText("");
        }

        else if (parseUser.get("profileHobbies") == null)
        {
            editProfileHobbies.setText("");
        }

        else if (parseUser.get("profileFavouriteSports") == null)
        {
            editProfileFavouriteSports.setText("");
        }
        else {
            editProfileName.setText(parseUser.get("profileName") + "");
            editProfileBio.setText(parseUser.get("profileBio") + "");
            editProfileProfession.setText(parseUser.get("profileProfession") + "");
            editProfileHobbies.setText(parseUser.get("profileHobbies") + "");
            editProfileFavouriteSports.setText(parseUser.get("profileFavouriteSports") + "");
        }

        btnProfileUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                parseUser.put("profileName", editProfileName.getText().toString());
                parseUser.put("profileBio", editProfileBio.getText().toString());
                parseUser.put("profileProfession", editProfileProfession.getText().toString());
                parseUser.put("profileHobbies", editProfileHobbies.getText().toString());
                parseUser.put("profileFavouriteSports",
                        editProfileFavouriteSports.getText().toString());


                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Updating profile in progress");
                progressDialog.show();

                parseUser.saveInBackground(new SaveCallback()
                {
                    @Override
                    public void done(ParseException e)
                    {
                        if (e == null)
                        {
                            FancyToast.makeText(getContext(), "Profile updated successfully.",
                                    FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
                        }

                        else
                        {
                            FancyToast.makeText(getContext(), e.getMessage(), FancyToast.LENGTH_LONG,
                                    FancyToast.ERROR, true).show();
                        }

                        progressDialog.dismiss();
                    }
                });
            };
        });
        return view;
    };
}


