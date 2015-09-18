package com.example.pandasonic;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pandasonic.dummy.DummyContent;

/**
 * A fragment representing a single Customer detail screen. This fragment is
 * either contained in a {@link CustomerListActivity} in two-pane mode (on
 * tablets) or a {@link CustomerDetailActivity} on handsets.
 */
public class CustomerDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private CustomerInfoItem mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public CustomerDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = DummyContent.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_customer_detail,
				container, false);

		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.customer_detail))
					.setText(
						PandaSonicContract.CustomerInfo.COLUMN_NAME_PERSON_NAME + ": " + mItem.getName() + "\n" +
						PandaSonicContract.CustomerInfo.COLUMN_NAME_PHONE + ": " + mItem.getPhone() + "\n" + 
						PandaSonicContract.CustomerInfo.COLUMN_NAME_ADDRESS + ": " + mItem.getAddress() + "\n" +
						PandaSonicContract.CustomerInfo.COLUMN_NAME_APT + ": " + mItem.getApt());
		}

		return rootView;
	}
}