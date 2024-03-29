package com.starklabs.classschedulingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.transition.Transition;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    com.starklabs.classschedulingsystem.MyTextView mon1, mon2, mon3, mon4, mon5, mon6, mon7, mon8, tue1, tue2, tue3, tue4, tue5, tue6, tue7, tue8,
            wed1, wed2, wed3, wed4, wed5, wed6, wed7, wed8, thu1, thu2, thu3, thu4, thu5, thu6, thu7, thu8,
            fri1, fri2, fri3, fri4, fri5, fri6, fri7, fri8;
    int sender;
    MyTextView tempText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("token:",token);

        sender = getIntent().getIntExtra("Type", 0);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Topic",0);
        if(sender==1 && sharedPreferences.getBoolean("isInstructorSubscribed",false))
        {
            Toast.makeText(MainActivity.this,"Topic:"+sharedPreferences.getBoolean("isInstructorSubscribed",false),Toast.LENGTH_SHORT).show();
        }
        else if(sender==1 && !sharedPreferences.getBoolean("isInstructorSubscribed",false))
        {
                FirebaseMessaging.getInstance().subscribeToTopic("Instructor").addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(!task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this,"Couldn't subscribe",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Subscribed Instructor",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isInstructorSubscribed",true);
            editor.commit();
        }
        if(sender==2 && sharedPreferences.getBoolean("isStudentSubscribed",false))
        {
            Toast.makeText(MainActivity.this,"Topic:"+sharedPreferences.getBoolean("isStudentSubscribed",false),Toast.LENGTH_SHORT).show();
        }
        else if(sender==2 && !sharedPreferences.getBoolean("isStudentSubscribed",false))
        {
            FirebaseMessaging.getInstance().subscribeToTopic("Student").addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(!task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this,"Couldn't subscribe",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Subscribed Student",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isStudentSubscribed",true);
            editor.commit();
        }
        mon1 = findViewById(R.id.mon1);
        mon2 = findViewById(R.id.mon2);
        mon3 = findViewById(R.id.mon3);
        mon4 = findViewById(R.id.mon4);
        mon5 = findViewById(R.id.mon5);
        mon6 = findViewById(R.id.mon6);
        mon7 = findViewById(R.id.mon7);
        mon8 = findViewById(R.id.mon8);

        tue1 = findViewById(R.id.tue1);
        tue2 = findViewById(R.id.tue2);
        tue3 = findViewById(R.id.tue3);
        tue4 = findViewById(R.id.tue4);
        tue5 = findViewById(R.id.tue5);
        tue6 = findViewById(R.id.tue6);
        tue7 = findViewById(R.id.tue7);
        tue8 = findViewById(R.id.tue8);

        wed1 = findViewById(R.id.wed1);
        wed2 = findViewById(R.id.wed2);
        wed3 = findViewById(R.id.wed3);
        wed4 = findViewById(R.id.wed4);
        wed5 = findViewById(R.id.wed5);
        wed6 = findViewById(R.id.wed6);
        wed7 = findViewById(R.id.wed7);
        wed8 = findViewById(R.id.wed8);

        thu1 = findViewById(R.id.thu1);
        thu2 = findViewById(R.id.thu2);
        thu3 = findViewById(R.id.thu3);
        thu4 = findViewById(R.id.thu4);
        thu5 = findViewById(R.id.thu5);
        thu6 = findViewById(R.id.thu6);
        thu7 = findViewById(R.id.thu7);
        thu8 = findViewById(R.id.thu8);

        fri1 = findViewById(R.id.fri1);
        fri2 = findViewById(R.id.fri2);
        fri3 = findViewById(R.id.fri3);
        fri4 = findViewById(R.id.fri4);
        fri5 = findViewById(R.id.fri5);
        fri6 = findViewById(R.id.fri6);
        fri7 = findViewById(R.id.fri7);
        fri8 = findViewById(R.id.fri8);

        // Listeners are set below

        mon1.setOnClickListener(this);
        mon2.setOnClickListener(this);
        mon3.setOnClickListener(this);
        mon4.setOnClickListener(this);
        mon5.setOnClickListener(this);
        mon6.setOnClickListener(this);
        mon7.setOnClickListener(this);
        mon8.setOnClickListener(this);

        tue1.setOnClickListener(this);
        tue2.setOnClickListener(this);
        tue3.setOnClickListener(this);
        tue4.setOnClickListener(this);
        tue5.setOnClickListener(this);
        tue6.setOnClickListener(this);
        tue7.setOnClickListener(this);
        tue8.setOnClickListener(this);

        wed1.setOnClickListener(this);
        wed2.setOnClickListener(this);
        wed3.setOnClickListener(this);
        wed4.setOnClickListener(this);
        wed5.setOnClickListener(this);
        wed6.setOnClickListener(this);
        wed7.setOnClickListener(this);
        wed8.setOnClickListener(this);

        thu1.setOnClickListener(this);
        thu2.setOnClickListener(this);
        thu3.setOnClickListener(this);
        thu4.setOnClickListener(this);
        thu5.setOnClickListener(this);
        thu6.setOnClickListener(this);
        thu7.setOnClickListener(this);
        thu8.setOnClickListener(this);

        fri1.setOnClickListener(this);
        fri2.setOnClickListener(this);
        fri3.setOnClickListener(this);
        fri4.setOnClickListener(this);
        fri5.setOnClickListener(this);
        fri6.setOnClickListener(this);
        fri7.setOnClickListener(this);
        fri8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mon5:
            case R.id.tue5:
            case R.id.wed5:
            case R.id.thu5:
            case R.id.fri5:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                final View view = inflater.inflate(R.layout.lunch_break, null);
                builder.setView(view);
                builder.create().show();
                break;

            default:
                tempText = (MyTextView) v;
                if (tempText.getStatus() == 0 && sender == 1) {
                    AlertDialog.Builder fixedBuilder = new AlertDialog.Builder(MainActivity.this);
                    fixedBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String url = "http://192.168.43.242:8080/scheduleing/Slot/Cancel/" + tempText.getSlotid() + "/" + getIntent().getStringExtra("profid") + "/";
                            RequestQueue tempQueue = Volley.newRequestQueue(MainActivity.this);
                            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.equals("success")) {
                                        Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                                        tempText.setStatus(1);
                                        tempText.setProfid(null);
                                        tempText.setSubjectid(null);
                                        tempText.setBackgroundColor(getResources().getColor(R.color.colorLightPrimary, null));
                                        tempText.setText("Empty");
                                    } else if (response.equals("abort")) {
                                        Toast.makeText(MainActivity.this, "Couldn't cancel the class", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(MainActivity.this, "Couldn't connect to server", Toast.LENGTH_SHORT).show();
                                }
                            });
                            tempQueue.add(stringRequest);
                        }
                    });
                    fixedBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    fixedBuilder.setMessage("Do you want cancel the class:" + tempText.getSubjectid() + "\n" + tempText.getProfid());
                    fixedBuilder.create().show();
                } else if (tempText.getStatus() == 2 && sender == 1) {
                    AlertDialog.Builder onGoingBuilder = new AlertDialog.Builder(MainActivity.this);
                    onGoingBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String url = "http://192.168.43.242:8080/scheduleing/Slot/Cancel/" + tempText.getSlotid() + "/" + getIntent().getStringExtra("profid");
                            RequestQueue tempQueue = Volley.newRequestQueue(MainActivity.this);
                            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.equals("success")) {
                                        Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                                        tempText.setStatus(1);
                                        tempText.setProfid(null);
                                        tempText.setSubjectid(null);
                                        tempText.setBackgroundColor(getResources().getColor(R.color.colorLightPrimary, null));
                                        tempText.setText("Empty");
                                    } else if (response.equals("abort")) {
                                        Toast.makeText(MainActivity.this, "Couldn't cancel the request", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Snackbar.make(findViewById(android.R.id.content), "Error connecting the server\n" + error, Snackbar.LENGTH_LONG).show();
                                }
                            });
                            tempQueue.add(stringRequest);
                        }
                    });
                    onGoingBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    onGoingBuilder.setMessage("Do you want to cancel the request:" + tempText.getSubjectid() + "\n" + tempText.getProfid());
                    onGoingBuilder.create().show();
                } else if (tempText.getStatus() == 1 && sender == 1) {
                    final AlertDialog.Builder request = new AlertDialog.Builder(MainActivity.this);
                    View viewInflater = LayoutInflater.from(this).inflate(R.layout.input_alert, null);
                    final TextInputEditText input_prof;
                    input_prof = viewInflater.findViewById(R.id.input_prof);
                    request.setView(viewInflater);

                    request.setPositiveButton("submit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final String course = input_prof.getText().toString().trim();
                            final String prof = getIntent().getStringExtra("profid");
                            if (course.equals("") || course == null) {
                                Toast.makeText(MainActivity.this, "Course ID can't be empty", Toast.LENGTH_SHORT).show();
                            } else {
                                String url = "http://192.168.43.242:8080/scheduleing/Slot/Update/" + tempText.getSlotid() + "/" + getIntent().getStringExtra("profid") + "/" + course + "/";
                                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equals("success")) {
                                            tempText.setBackgroundColor(getResources().getColor(R.color.colorAccent, null));
                                            tempText.setText(course + "\n" + prof);
                                            tempText.setSubjectid(course);
                                            tempText.setProfid(prof);
                                            tempText.setStatus(2);
                                        } else {
                                            Toast.makeText(MainActivity.this,"Couldn't schedule the class",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(MainActivity.this,"Couldn't connect to server",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                requestQueue.add(stringRequest);
                            }

                        }

                    }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    request.setMessage("Schedule Class:");
                    request.create().show();

                } else if (tempText.getStatus() == 2 && sender == 2) {
                    AlertDialog.Builder forVote = new AlertDialog.Builder(MainActivity.this);
                    forVote.setMessage("Vote for this class\n Choose your vote!!!");
                    forVote.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String url = "http://192.168.43.242:8080/scheduleing/Vote/Post/" + getIntent().getStringExtra("studentid") + "/" + tempText.getSlotid() + "/yes/";
                            RequestQueue studentVoteQueue = Volley.newRequestQueue(MainActivity.this);
                            StringRequest voteRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.equals("valid")) {
                                        Toast.makeText(MainActivity.this, "Voted Successfully", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Can't vote more than once", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(MainActivity.this, "Couldn't connect to server" + error, Toast.LENGTH_SHORT).show();
                                }
                            });
                            studentVoteQueue.add(voteRequest);
                        }
                    });
                    forVote.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String url = "http://192.168.43.242:8080/scheduleing/Vote/Post/" + getIntent().getStringExtra("studentid") + "/" + tempText.getSlotid() + "/no/";
                            RequestQueue studentVoteQueue = Volley.newRequestQueue(MainActivity.this);
                            StringRequest voteRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.equals("valid")) {
                                        Toast.makeText(MainActivity.this, "Voted successfully", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Can't vote more than once", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(MainActivity.this, "Couldn't connect to server" + error, Toast.LENGTH_SHORT).show();
                                }
                            });
                            studentVoteQueue.add(voteRequest);
                        }
                    });
                    forVote.setCancelable(false);
                    forVote.create().show();
                }
        }
    }

    @Override
    protected void onResume() {
        String url = "http://192.168.43.242:8080/scheduleing/Slot/Get/";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject tempObj = response.getJSONObject(i);
                        String slotid = tempObj.getString("slotid");
                        String profid = tempObj.getString("profid");
                        String status = tempObj.getString("status");
                        String subjectid = tempObj.getString("subjectid");
                        MyTextView myTextView = findBySlotId(slotid);
                        if (myTextView != null) {
                            myTextView.setSlotid(slotid);
                            if (status.equals("null")) {
                                myTextView.setText("Empty");
                                myTextView.setStatus(1);
                                myTextView.setSubjectid(subjectid);
                                myTextView.setProfid(profid);
                            } else if (status.equals("ongoing")) {
                                myTextView.setText(subjectid + "\n" + profid);
                                myTextView.setBackgroundColor(getResources().getColor(R.color.colorAccent, null));
                                myTextView.setStatus(2);
                                myTextView.setSubjectid(subjectid);
                                myTextView.setProfid(profid);
                            } else if (status.equals("filled")) {
                                myTextView.setText(subjectid + "\n" + profid);
                                myTextView.setBackgroundColor(getResources().getColor(R.color.lightYellow, null));
                                myTextView.setStatus(0);
                                myTextView.setSubjectid(subjectid);
                                myTextView.setProfid(profid);
                            }
                        }
                    } catch (JSONException e) {
                        Toast.makeText(MainActivity.this, "JSON Object Exception", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error: Couldn't connect to server" + error, Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
        super.onResume();
    }

    private MyTextView findBySlotId(String slotid) {
        if (slotid.equals("mon1"))
            return findViewById(R.id.mon1);
        if (slotid.equals("mon2"))
            return findViewById(R.id.mon2);
        if (slotid.equals("mon3"))
            return findViewById(R.id.mon3);
        if (slotid.equals("mon4"))
            return findViewById(R.id.mon4);
        if (slotid.equals("mon5"))
            return findViewById(R.id.mon6);
        if (slotid.equals("mon6"))
            return findViewById(R.id.mon7);
        if (slotid.equals("mon7"))
            return findViewById(R.id.mon8);

        if (slotid.equals("tue1"))
            return findViewById(R.id.tue1);
        if (slotid.equals("tue2"))
            return findViewById(R.id.tue2);
        if (slotid.equals("tue3"))
            return findViewById(R.id.tue3);
        if (slotid.equals("tue4"))
            return findViewById(R.id.tue4);
        if (slotid.equals("tue5"))
            return findViewById(R.id.tue6);
        if (slotid.equals("tue6"))
            return findViewById(R.id.tue7);
        if (slotid.equals("tue7"))
            return findViewById(R.id.tue8);


        if (slotid.equals("wed1"))
            return findViewById(R.id.wed1);
        if (slotid.equals("wed2"))
            return findViewById(R.id.wed2);
        if (slotid.equals("wed3"))
            return findViewById(R.id.wed3);
        if (slotid.equals("wed4"))
            return findViewById(R.id.wed4);
        if (slotid.equals("wed5"))
            return findViewById(R.id.wed6);
        if (slotid.equals("wed6"))
            return findViewById(R.id.wed7);
        if (slotid.equals("wed7"))
            return findViewById(R.id.wed8);


        if (slotid.equals("thu1"))
            return findViewById(R.id.thu1);
        if (slotid.equals("thu2"))
            return findViewById(R.id.thu2);
        if (slotid.equals("thu3"))
            return findViewById(R.id.thu3);
        if (slotid.equals("thu4"))
            return findViewById(R.id.thu4);
        if (slotid.equals("thu5"))
            return findViewById(R.id.thu6);
        if (slotid.equals("thu6"))
            return findViewById(R.id.thu7);
        if (slotid.equals("thu7"))
            return findViewById(R.id.thu8);

        if (slotid.equals("fri1"))
            return findViewById(R.id.fri1);
        if (slotid.equals("fri2"))
            return findViewById(R.id.fri2);
        if (slotid.equals("fri3"))
            return findViewById(R.id.fri3);
        if (slotid.equals("fri4"))
            return findViewById(R.id.fri4);
        if (slotid.equals("fri5"))
            return findViewById(R.id.fri6);
        if (slotid.equals("fri6"))
            return findViewById(R.id.fri7);
        if (slotid.equals("fri7"))
            return findViewById(R.id.fri8);

        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.logout) {
            SharedPreferences sharedPreferences = getSharedPreferences("LoginInfo", 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, FirstActivity.class);
            startActivity(intent);
            finish();
        }
        if (item.getItemId() == R.id.instruction)
        {
            Intent intent = new Intent(this,InstructionActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.about)
        {
            Intent intent = new Intent(this,AboutActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder exitDialog = new AlertDialog.Builder(this);
        exitDialog.setMessage("Do you want to exit?");
        exitDialog.setCancelable(false);
        exitDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        exitDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        exitDialog.create().show();
        //super.onBackPressed();
    }
}
