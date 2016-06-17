package com.iappdragon.recruitmenttest.Utilities;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.iappdragon.recruitmenttest.database.DatabaseHelper;
import com.iappdragon.recruitmenttest.model.Contact;

public class AsyncTaskHandler extends AsyncTask<String, Void, String> {

    public static final int GET_INFO = 1;
    Activity callerActivity;

    int action_id;

    int responseCode;
    String returnObject = null;

    public AsyncTaskHandler(Activity caller, int action) {

        this.callerActivity = caller;
        this.action_id = action;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        switch (this.action_id) {
            case GET_INFO:

                break;
            default:
                break;
        }
    }

    @Override
    protected String doInBackground(String... strings) {

        switch (this.action_id) {
            case GET_INFO:
                DatabaseHelper db = new DatabaseHelper(callerActivity);
                // Inserting Contacts
                Log.d("Insert: ", "Inserting ..");
                Contact contact = new Contact("test", "0191112123");
                db.addContact(contact);
                returnObject = "name :"+contact.getName()+ "   number :"+contact.getPhoneNumber();
                break;

            default:
                break;
        }
        return returnObject;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        switch (this.action_id) {
            case GET_INFO:
                ((AsyncTaskListener) callerActivity).onPostExecute(result,
                        responseCode, action_id);
                Toast.makeText(callerActivity, returnObject, Toast.LENGTH_SHORT)
                        .show();
                break;

            default:
                break;
        }
    }

    @Override
    protected void onCancelled(String data) {
        super.onCancelled(data);

        ((AsyncTaskListener) callerActivity).onInternetNotAvailable(data,
                action_id);

    }

}
