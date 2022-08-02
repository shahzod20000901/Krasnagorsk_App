package com.example.krasnagorsk_app.Address;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.krasnagorsk_app.R;

import java.util.ArrayList;

public class MyExpandableAdapter extends BaseExpandableListAdapter {
    private Context context;
    private String[] oblastList={ "Ташкентская область",
            "Наманганская область",
            "Ферганская область",
            "Андижанская область",
            "Сирдарьинская область",
            "Бухарская область",
            "Кашкадарьинская область",
            "Самаркандская область",
            "Сурхандарьинская область",
            "Хорезмская область",
            "Навоийская область",
            "Джиззакская область",
            "Республика Каракалпакистан"};

    private String[] Tashkent_Oblast_District={"Паркенский район",
            "Бостанлыкский район",
            "Бекабадский район",
            "Бекабадский район",
            "Юкоричирчикский район",
            "Чиназский район",
            "Уртачирчикский район",
            "Ташкентский район",
            "Пскентский район",
            "Куйичирчикский район",
            "Кибрайский район"};

    private String[] Namangan_Oblast_District={"Наманган (город)",
            "Янгикурганский район",
            "Чустский  район",
            "Чартакский район",
            "Учкурганский район",
            "Уйчинский район",
            "Туракурганский  район",
            "Папский район",
            "Нарынский район",
            "Наманганский район",
            "Мингбулакский район",
            "Касансайский район",
            "Наманган (город)"
    };

    public MyExpandableAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return 13;
    }

    @Override
    public int getChildrenCount(int i) {
        return 15;
    }

    @Override
    public Object getGroup(int i) {
        return oblastList[i];
    }

    @Override
    public Object getChild(int i, int i1) {
        if(oblastList[i]=="Ташкентская область")
        {
            return Tashkent_Oblast_District[i1];
        }
        else if(oblastList[i]=="Наманганская область")
        {
            return Namangan_Oblast_District[i1];
        }

        else
        {
            return 0;
        }
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.address_oblast, null);
        }

        switch (i) {
            case 0: {
                ((TextView) view.findViewById(R.id.tv_oblast)).setText(oblastList[0]);
            }
            break;
            case 1: {
                ((TextView) view.findViewById(R.id.tv_oblast)).setText(oblastList[1]);
            }
            break;
            case 2: {
                ((TextView) view.findViewById(R.id.tv_oblast)).setText(oblastList[2]);
            }
            break;
            case 3: {
                ((TextView) view.findViewById(R.id.tv_oblast)).setText(oblastList[3]);
            }
            break;
            case 4: {
                ((TextView) view.findViewById(R.id.tv_oblast)).setText(oblastList[4]);
            }
            break;
            case 5: {
                ((TextView) view.findViewById(R.id.tv_oblast)).setText(oblastList[5]);
            }
            break;
            case 6: {
                ((TextView) view.findViewById(R.id.tv_oblast)).setText(oblastList[6]);
            }
            break;
            case 7: {
                ((TextView) view.findViewById(R.id.tv_oblast)).setText(oblastList[7]);
            }
            break;
            case 8: {
                ((TextView) view.findViewById(R.id.tv_oblast)).setText(oblastList[8]);
            }
            break;
            case 9: {
                ((TextView) view.findViewById(R.id.tv_oblast)).setText(oblastList[9]);
            }
            break;
            case 10: {
                ((TextView) view.findViewById(R.id.tv_oblast)).setText(oblastList[10]);
            }
            break;
            case 11: {
                ((TextView) view.findViewById(R.id.tv_oblast)).setText(oblastList[11]);
            }
            break;
            case 12: {
                ((TextView) view.findViewById(R.id.tv_oblast)).setText(oblastList[12]);
            }
            break;
        }

        return view;

    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.address_oblast_child_district, null);

        switch (i) {
            case 0: {
                switch (i1) {
                    case 0: {
                        ((TextView) view.findViewById(R.id.tv_district)).setText(Tashkent_Oblast_District[0]);
                    }
                    break;
                    case 1: {
                        ((TextView) view.findViewById(R.id.tv_district)).setText(Tashkent_Oblast_District[1]);
                    }
                    break;
                    case 2: {
                        ((TextView) view.findViewById(R.id.tv_district)).setText(Tashkent_Oblast_District[2]);
                    }
                    break;
                    case 3: {
                        ((TextView) view.findViewById(R.id.tv_district)).setText(Tashkent_Oblast_District[3]);
                    }
                    break;
                    case 4: {
                        ((TextView) view.findViewById(R.id.tv_district)).setText(Tashkent_Oblast_District[4]);
                    }
                    break;
                    case 5: {
                        ((TextView) view.findViewById(R.id.tv_district)).setText(Tashkent_Oblast_District[5]);
                    }
                    break;
                    case 6: {
                        ((TextView) view.findViewById(R.id.tv_district)).setText(Tashkent_Oblast_District[6]);
                    }
                    break;
                    case 7: {
                        ((TextView) view.findViewById(R.id.tv_district)).setText(Tashkent_Oblast_District[7]);
                    }
                    break;
                    case 8: {
                        ((TextView) view.findViewById(R.id.tv_district)).setText(Tashkent_Oblast_District[8]);
                    }
                    break;
                    case 9: {
                        ((TextView) view.findViewById(R.id.tv_district)).setText(Tashkent_Oblast_District[9]);
                    }
                    break;
                }
            }
            break;
            case 1: {
                switch (i1) {
                    case 0: {
                        ((TextView) view.findViewById(R.id.tv_district)).setText(Namangan_Oblast_District[0]);
                    }
                    break;
                    case 1: {
                        ((TextView) view.findViewById(R.id.tv_district)).setText(Namangan_Oblast_District[1]);
                    }
                    break;
                    case 2: {
                        ((TextView) view.findViewById(R.id.tv_district)).setText(Namangan_Oblast_District[2]);
                    }
                    break;
                    case 3: {
                        ((TextView) view.findViewById(R.id.tv_district)).setText(Namangan_Oblast_District[3]);
                    }
                    break;
                    case 4: {
                        ((TextView) view.findViewById(R.id.tv_district)).setText(Namangan_Oblast_District[4]);
                    }
                    break;
                    case 5: {
                        ((TextView) view.findViewById(R.id.tv_district)).setText(Namangan_Oblast_District[5]);
                    }
                    break;
                    case 6: {
                        ((TextView) view.findViewById(R.id.tv_district)).setText(Namangan_Oblast_District[6]);
                    }
                    break;
                    case 7: {
                        ((TextView) view.findViewById(R.id.tv_district)).setText(Namangan_Oblast_District[7]);
                    }
                    break;
                    case 8: {
                        ((TextView) view.findViewById(R.id.tv_district)).setText(Namangan_Oblast_District[8]);
                    }
                    break;
                    case 9: {
                        ((TextView) view.findViewById(R.id.tv_district)).setText(Namangan_Oblast_District[9]);
                    }
                    break;
                    case 10: {
                        ((TextView) view.findViewById(R.id.tv_district)).setText(Namangan_Oblast_District[10]);
                    }
                    break;
                    case 11: {
                        ((TextView) view.findViewById(R.id.tv_district)).setText(Namangan_Oblast_District[11]);
                    }
                    break;
                }
            }
            break;


        }
        return view;

    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

}
