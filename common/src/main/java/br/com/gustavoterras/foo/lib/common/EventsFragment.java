package br.com.gustavoterras.foo.lib.common;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import br.com.gustavoterras.foo.lib.common.databinding.FragmentEventsBinding;
import br.com.gustavoterras.foo.lib.core.network.ConsumerService;
import br.com.gustavoterras.foo.lib.core.util.Constants;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentEventsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_events, container, false);

        final ListView listView = binding.list;

        ConsumerService.INSTANCE.getEvents()
                .subscribe(new Observer<List<Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Object> value) {

                        List<String> events = new ArrayList<>();

                        for (Object o : value) {
                            events.add(((LinkedTreeMap<String, String>) o).get("title"));
                        }

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                String activityToStart = "br.com.gustavoterras.lib.LibMyActivity";
                                try {
                                    Class<?> c = Class.forName(activityToStart);
                                    Intent intent = new Intent(getActivity(), c);
                                    startActivity(intent);
                                } catch (ClassNotFoundException ignored) {
                                    Log.e("TAG", "xablau");
                                }
                            }
                        });

                        listView.setAdapter(new ArrayAdapter<>(Objects.requireNonNull(getActivity()),
                                android.R.layout.simple_list_item_1, events));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return binding.getRoot();
    }
}
