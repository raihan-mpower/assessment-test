package com.iappdragon.recruitmenttest.Utilities;

public interface AsyncTaskListener {

    public void onPreExecute(int action_id);

    public void onPostExecute(String result, int responseCode, int action_id);

    public void onError(Exception e);

    public void onInternetNotAvailable(String result, int action_id);

}
