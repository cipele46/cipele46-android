package eu.fiveminutes.cipele46.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import eu.fiveminutes.cipele46.R;


public class ProgressDialogFragment extends DialogFragment {

	@Override
	public Dialog onCreateDialog(final Bundle savedInstanceState) {
		final ProgressDialog dialog = new ProgressDialog(getActivity());

		dialog.setMessage(getString(R.string.please_wait));
		dialog.setIndeterminate(true);
		dialog.setCancelable(false);
		
		return dialog;
	}

}
