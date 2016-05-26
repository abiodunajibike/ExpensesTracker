package com.abiodunajibike.app.expensestracker.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abiodunajibike.app.expensestracker.Expense;
import com.abiodunajibike.app.expensestracker.R;
import com.abiodunajibike.app.expensestracker.helpers.DatabaseController;
import com.abiodunajibike.app.expensestracker.helpers.ReUsableClasses;

import java.util.Calendar;

/**
 * Created by AJ on 11/05/2016.
 */
public class AddExpenseFragment extends Fragment {

    private DatePickerDialog fromDatePickerDialog;

    DatabaseController DBController;

    //@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        DBController = new DatabaseController(getActivity());

        return inflater.inflate(R.layout.primary_layout, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        final TextView datePickerTextView = (TextView) view.findViewById(R.id.datePicker);
        datePickerTextView.setInputType(InputType.TYPE_NULL);
        datePickerTextView.requestFocus();

        view.findViewById(R.id.datePicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar calendar = Calendar.getInstance();
                int yyyy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date =  String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear) + "-" + String.valueOf(year);
                        datePickerTextView.setText(date);
                    }
                }, dd, mm, yyyy);
                //initialize datepicker calender with today's date
                datePicker.updateDate(yyyy, mm, dd);
                datePicker.show();

            }
        });

        final EditText amount = (EditText) view.findViewById(R.id.amount);
        amount.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                amount.removeTextChangedListener(this);

                try {
                    String givenstring = s.toString();
                    double doubleleVal;
                    if (givenstring.contains(",")) {
                        givenstring = givenstring.replaceAll(",", "");
                    }
                    doubleleVal = Double.parseDouble(givenstring);
                    /**
                     * longval = Long.parseLong(givenstring);
                    DecimalFormat formatter = new DecimalFormat("#,###,###");
                    String formattedString = formatter.format(longval);
                    **/
                    String formattedString = ReUsableClasses.formatValue(doubleleVal);
                    amount.setText(formattedString);
                    amount.setSelection(amount.getText().length());
                    // to place the cursor at the end of text
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                amount.addTextChangedListener(this);

            }
        });

        final EditText description = (EditText) view.findViewById(R.id.description);


        Button add_expense_btn = (Button) view.findViewById(R.id.add_expense_btn);

        add_expense_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity().getApplicationContext(), description.getText(), Toast.LENGTH_LONG).show();

                //Toast.makeText(getActivity().getApplicationContext(), amount.getText(), Toast.LENGTH_LONG).show();

                //Toast.makeText(getActivity().getApplicationContext(), datePickerTextView.getText(), Toast.LENGTH_LONG).show();

                //Add expense
                String description_text = description.getText().toString();
                String amount_string = amount.getText().toString();
                double amount_val       = Double.parseDouble(amount_string.replaceAll(",", ""));
                String date_text        = datePickerTextView.getText().toString();

                if (TextUtils.isEmpty(description_text) || TextUtils.isEmpty(amount_string) || TextUtils.isEmpty(date_text)){
                    Toast.makeText(getActivity().getApplicationContext(), getActivity().getApplicationContext().getString(R.string.add_expense_error), Toast.LENGTH_LONG).show();
                }else{
                    addExpense(description_text, amount_val, date_text);

                    //ExpensesListViewFragment nextFrag = new ExpensesListViewFragment();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction
                            .replace(R.id.containerView, new ExpensesListViewFragment())
                            .addToBackStack(null)
                            .commit();
                }

            }

        });


    }

    public void addExpense(String description, double amount, String date){
        Expense expense = new Expense(description, amount, date);
        DBController.addExpense(expense);
    }





}


