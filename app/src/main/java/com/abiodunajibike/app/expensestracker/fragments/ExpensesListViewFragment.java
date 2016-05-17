package com.abiodunajibike.app.expensestracker.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.abiodunajibike.app.expensestracker.R;

/**
 * Created by AJ on 12/05/2016.
 */
public class ExpensesListViewFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.expenses_list, container, false);
        String[] values = new String[] { "Food | 20,000 | 08-09-2016", "Fuel | 20,000 | 08-09-2016", "Help | 20,000 | 08-09-2016" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
        return rootView;
        //return inflater.inflate(R.layout.expenses_list, null);
    }
}
