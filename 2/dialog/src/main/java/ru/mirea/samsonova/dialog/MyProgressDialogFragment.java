package ru.mirea.samsonova.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class MyProgressDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Загрузка данных");
        progressDialog.setMessage("Пожалуйста, подождите...");
        progressDialog.setIndeterminate(true);

        progressDialog.setButton(Dialog.BUTTON_NEGATIVE, "Отмена", (dialog, which) -> dialog.cancel());

        return progressDialog;
    }
}