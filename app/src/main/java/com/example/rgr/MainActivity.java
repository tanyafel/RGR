package com.example.rgr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rgr.JavaModule.BigList;
import com.example.rgr.JavaModule.SmallList;
import com.example.rgr.JavaModule.Types.CartVector2D;
import com.example.rgr.JavaModule.Types.Dble;
import com.example.rgr.JavaModule.UserFactory;

public class MainActivity extends AppCompatActivity {

    private BigList BigList = new BigList();
    private double grade_of_two = 3; // для битонической сортировки
    private int userType = 0; // по умолчанию тип Double


    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button acceptDbleType;
    private Button acceptVector2DType;
    private Button closeBtn;
    private Button printListBtn;
    private Button balanceListBtn;
    private Button sortListBtn;
    private Button pushBackListBtn;
    private Button getOnPosListBtn;
    private Button insertOnPosListBtn;
    private Button removeOnPosListBtn;
    private Button changeTypeListBtn;

    public void createNewUserTypeDialog()
    {
        dialogBuilder = new AlertDialog.Builder(this);
        final View createNewUserTypePopupView = getLayoutInflater().inflate(R.layout.selectusertype_popup, null);

        acceptDbleType = (Button) createNewUserTypePopupView.findViewById(R.id.selectDblePopup);
        acceptVector2DType = (Button) createNewUserTypePopupView.findViewById(R.id.selectVector2DPopup);
        closeBtn = (Button) createNewUserTypePopupView.findViewById(R.id.closePopup);

        dialogBuilder.setView(createNewUserTypePopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        acceptDbleType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userType = 0;
                generate_list(userType);
                dialog.dismiss();
            }
        });

        acceptVector2DType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userType = 1;
                generate_list(userType);
                dialog.dismiss();
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void generate_list(int user_type) {

        BigList.remove_list();

        System.out.println(user_type);

        if (UserFactory.get_type_name_list().get(user_type).equals("Double")) {
            System.out.println(UserFactory.get_type_name_list().get(user_type));

            double count = 100;
            for (int j = 0; j < 3; j++) {
                BigList.push(new SmallList());

                for (int i = 0; i < 3; i++) {
                    double value = Math.round(((Math.random() * (100 - 1 + 1)) + 1 + 1)* count) / count;
                    BigList.push(new Dble(value));
                }
            }
        } else if (UserFactory.get_type_name_list().get(user_type).equals("Vector2D")) {
            System.out.println(UserFactory.get_type_name_list().get(user_type));

            for (int j = 0; j < 3; j++) {
                BigList.push(new SmallList());

                for (int i = 0; i < 3; i++) {
                    int x = ((int) (Math.random() * ((100 - 1) + 1)) + 1) + 1;
                    int y = ((int) (Math.random() * ((100 - 1) + 1)) + 1) + 1;
                    BigList.push(new CartVector2D(x, y));
                }
            }

        } else {
            System.out.println("Not available type of data");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNewUserTypeDialog();

        // ----------------------------------------------------------------------

        printListBtn = (Button) findViewById(R.id.printListBtn);

        printListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                a_builder.setMessage("List data:\n" + BigList.print_list()).setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).setIcon(android.R.drawable.ic_dialog_info);

                AlertDialog alertDialog = a_builder.create();
                alertDialog.setTitle("List length :" + BigList.inner_count());
                alertDialog.show();
            }
        });

        // ----------------------------------------------------------------------

        balanceListBtn = (Button) findViewById(R.id.balanceListBtn);

        balanceListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                EditText balance_level = new EditText(a_builder.getContext());

                a_builder.setMessage("Type size of sublists:\n")
                        .setView(balance_level)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(!balance_level.getText().toString().isEmpty()

                                )
                                {
                                    try {
                                        Integer.parseInt(balance_level.getText().toString());

                                        AlertDialog.Builder before_balance = new AlertDialog.Builder(MainActivity.this);
                                        before_balance.setMessage("List before balancing:\n" + BigList.print_list()).setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                                AlertDialog.Builder after_balance = new AlertDialog.Builder(MainActivity.this);
                                                after_balance.setMessage("List after balancing:\n" + BigList.print_list()).setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.cancel();
                                                    }
                                                }).setIcon(android.R.drawable.ic_dialog_info);

                                                AlertDialog after_alertDialog = after_balance.create();
                                                after_alertDialog.setTitle("List length :" + BigList.inner_count());
                                                after_alertDialog.show();
                                            }
                                        }).setIcon(android.R.drawable.ic_dialog_info);

                                        AlertDialog before_alertDialog = before_balance.create();
                                        before_alertDialog.setTitle("List length :" + BigList.inner_count());
                                        before_alertDialog.show();

                                        BigList.balance_list((int) Math.abs(Math.ceil(Double.parseDouble(balance_level.getText().toString()))));

                                        dialogInterface.cancel();
                                    }
                                    catch (Exception e)
                                    {

                                    }
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).setIcon(android.R.drawable.ic_menu_manage);

                AlertDialog alertDialog = a_builder.create();
                alertDialog.setTitle("List balancing");

                alertDialog.show();
            }
        });

        // ----------------------------------------------------------------------

        sortListBtn = (Button) findViewById(R.id.sortListBtn);

        sortListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);

                a_builder.setMessage("List before sorting:\n" + BigList.print_list())
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                BigList.sort_list();
                                AlertDialog.Builder before_balance = new AlertDialog.Builder(MainActivity.this);
                                before_balance.setMessage("List after sorting:\n" + BigList.print_list())
                                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        }).setIcon(android.R.drawable.ic_dialog_info);

                                AlertDialog before_alertDialog = before_balance.create();
                                before_alertDialog.setTitle("Sorted list");
                                before_alertDialog.show();

                                dialogInterface.cancel();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).setIcon(android.R.drawable.ic_menu_manage);

                AlertDialog alertDialog = a_builder.create();
                alertDialog.setTitle("List Sorting");

                alertDialog.show();
            }
        });

        // ----------------------------------------------------------------------

        pushBackListBtn = (Button) findViewById(R.id.pushBackListBtn);

        pushBackListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder push_question = new AlertDialog.Builder(MainActivity.this);
                push_question.setMessage("Add item to the end of list?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (userType == 0)
                                {
                                    double count = 100;
                                    double value = Math.round(((Math.random() * (100 - 1 + 1)) + 1 + 1)* count) / count;
                                    BigList.push(new Dble(value));
                                }
                                if (userType == 1)
                                {
                                    int x = ((int) (Math.random() * ((100 - 1) + 1)) + 1) + 1;
                                    int y = ((int) (Math.random() * ((100 - 1) + 1)) + 1) + 1;
                                    BigList.push(new CartVector2D(x,y));
                                }

                                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                                a_builder.setMessage("Add another item?")
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        })
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                pushBackListBtn.callOnClick();
                                            }
                                        })
                                        .setIcon(android.R.drawable.ic_menu_help);

                                AlertDialog alertDialog = a_builder.create();
                                alertDialog.setTitle("Add more?");
                                alertDialog.show();

                                dialogInterface.cancel();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_menu_help);

                AlertDialog questionAlertDialog = push_question.create();
                questionAlertDialog.setTitle("List length :" + BigList.inner_count());
                questionAlertDialog.show();
            }
        });

        // ----------------------------------------------------------------------

        getOnPosListBtn = (Button) findViewById(R.id.getOnPosListBtn);

        getOnPosListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                EditText position_get = new EditText(a_builder.getContext());
                position_get.setHint("Position from 1 to " + BigList.inner_count());

                a_builder.setMessage("Type position to check item:\n")
                        .setView(position_get)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                try {
                                    Double.parseDouble(position_get.getText().toString());

                                    if (Math.ceil(Double.parseDouble(position_get.getText().toString())) <= BigList.inner_count()
                                            &&
                                            Math.ceil(Double.parseDouble(position_get.getText().toString())) > 0
                                            &&
                                            !position_get.getText().toString().isEmpty()
                                    )
                                    {
                                        AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);

                                        a_builder.setMessage("Item from position "
                                                        + (int) Math.abs(Math.ceil(Double.parseDouble(position_get.getText().toString())))
                                                        + ":\n"
                                                        + BigList.get_item_on_position((int) Math.abs(Math.ceil(Double.parseDouble(position_get.getText().toString()))))
                                                )
                                                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.cancel();
                                                    }
                                                }).setIcon(android.R.drawable.ic_dialog_info);

                                        AlertDialog alertDialog = a_builder.create();
                                        alertDialog.setTitle("Item on position");
                                        alertDialog.show();
                                    }
                                }
                                catch (Exception e)
                                {

                                }

                                dialogInterface.cancel();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info);

                AlertDialog alertDialog = a_builder.create();
                alertDialog.setTitle("List length :" + BigList.inner_count());
                alertDialog.show();
            }
        });

        // ----------------------------------------------------------------------

        insertOnPosListBtn = (Button) findViewById(R.id.insertOnPosListBtn);

        insertOnPosListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                EditText position_insert = new EditText(a_builder.getContext());
                position_insert.setHint("Position from 1 to " + BigList.inner_count());

                a_builder.setMessage("Type position to insert item:\n")
                        .setView(position_insert)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                try{
                                    Double.parseDouble(position_insert.getText().toString());

                                    if ((int) Math.ceil(Double.parseDouble(position_insert.getText().toString())) <= BigList.inner_count()
                                            &&
                                            Math.ceil(Double.parseDouble(position_insert.getText().toString())) > 0
                                            &&
                                            !position_insert.getText().toString().isEmpty()
                                    )
                                    {
                                        if(userType == 0)
                                        {
                                            AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                                            EditText value_insert = new EditText(a_builder.getContext());
                                            value_insert.setText("12.3");

                                            a_builder.setMessage("Type data (ex. 12.3):\n")
                                                    .setView(value_insert)
                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {

                                                            try {
                                                                Double.parseDouble(position_insert.getText().toString());

                                                                if (!value_insert.getText().toString().isEmpty()) {
                                                                    BigList.insert_item_on_position(
                                                                            (int) Math.ceil(Double.parseDouble(position_insert.getText().toString())),
                                                                            new Dble(Double.parseDouble(value_insert.getText().toString()))
                                                                    );
                                                                }
                                                            } catch (Exception e) {

                                                            }

                                                            dialogInterface.cancel();
                                                        }
                                                    })
                                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            dialogInterface.cancel();
                                                        }
                                                    })
                                                    .setIcon(android.R.drawable.ic_dialog_info);

                                            AlertDialog alertDialog = a_builder.create();
                                            alertDialog.setTitle("Type item data");
                                            alertDialog.show();
                                        }
                                        if(userType == 1)
                                        {
                                            AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                                            EditText value_insert = new EditText(a_builder.getContext());
                                            value_insert.setText("(12;13)");

                                            a_builder.setMessage("Type vector data (x;y), (ex. (12;13)):\n")
                                                    .setView(value_insert)
                                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {

                                                            String ss = value_insert.getText().toString();
                                                            String ss1 = ss.substring(1, ss.length()-1);
                                                            String[] vector2d = ss1.split(";");

                                                            try {
                                                                Integer.parseInt(vector2d[0]);
                                                                Integer.parseInt(vector2d[1]);
                                                            }
                                                            catch (Exception e)
                                                            {

                                                            }

                                                            if(!value_insert.getText().toString().isEmpty()) {

                                                                BigList.insert_item_on_position(
                                                                        (int) Math.ceil(Double.parseDouble(position_insert.getText().toString())),
                                                                        new CartVector2D(Integer.parseInt(vector2d[0]), Integer.parseInt(vector2d[1]))
                                                                );
                                                            }
                                                            dialogInterface.cancel();
                                                        }
                                                    })
                                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            dialogInterface.cancel();
                                                        }
                                                    })
                                                    .setIcon(android.R.drawable.ic_dialog_info);

                                            AlertDialog alertDialog = a_builder.create();
                                            alertDialog.setTitle("Insert item");
                                            alertDialog.show();
                                        }
                                    }
                                }
                                catch (Exception e) {

                                }
                                dialogInterface.cancel();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info);

                AlertDialog alertDialog = a_builder.create();
                alertDialog.setTitle("List length :" + BigList.inner_count());
                alertDialog.show();
            }
        });

        // ----------------------------------------------------------------------

        removeOnPosListBtn= (Button) findViewById(R.id.removeOnPosListBtn);

        removeOnPosListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                EditText position_remove = new EditText(a_builder.getContext());
                position_remove.setHint("Position from 1 to " + BigList.inner_count());

                a_builder.setMessage("Type position to remove item:\n" + BigList.print_list())
                        .setView(position_remove)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                try {
                                    Double.parseDouble(position_remove.getText().toString());

                                    if (Math.ceil(Double.parseDouble(position_remove.getText().toString())) <= BigList.inner_count()
                                            &&
                                            Math.ceil(Double.parseDouble(position_remove.getText().toString())) > 0
                                            &&
                                            !position_remove.getText().toString().isEmpty()
                                    ) {
                                        AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                                        a_builder.setMessage("Item ("
                                                        + BigList.get_item_on_position((int)Math.ceil(Double.parseDouble(position_remove.getText().toString())))
                                                        + ") on position "
                                                        + position_remove.getText().toString()
                                                        + " was removed!")
                                                .setPositiveButton("ОK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.cancel();
                                                    }
                                                })
                                                .setIcon(android.R.drawable.ic_dialog_alert);

                                        AlertDialog alertDialog = a_builder.create();
                                        alertDialog.setTitle("Removing item");
                                        alertDialog.show();

                                        BigList.remove_item_on_position((int) Math.ceil(Double.parseDouble(position_remove.getText().toString())));
                                    }
                                } catch (Exception e) {

                                }
                                dialogInterface.cancel();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info);

                AlertDialog alertDialog = a_builder.create();
                alertDialog.setTitle("List length :" + BigList.inner_count());
                alertDialog.show();
            }
        });

        // ----------------------------------------------------------------------

        changeTypeListBtn = (Button) findViewById(R.id.changeTypeListBtn);

        changeTypeListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createNewUserTypeDialog();
            }
        });
    }
}