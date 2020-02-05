package com.phjethva.mvvm_rx.ui.activity.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.phjethva.mvvm_rx.R;
import com.phjethva.mvvm_rx.data.model.WondersResponse;
import com.phjethva.mvvm_rx.databinding.MainActivityBinding;
import com.phjethva.mvvm_rx.ui.activity.BaseActivity;
import com.phjethva.mvvm_rx.utils.SharedPrefUtils;

import java.util.List;

public class MainActivity extends BaseActivity {

    private MainActivityBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.toolbar);

        setUp(savedInstanceState);

        binding.srlWonders.setColorSchemeResources(R.color.colorAccent);
        binding.srlWonders.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                populateAllWonders();
                binding.srlWonders.setRefreshing(false);
            }
        });

    }

    private void setUp(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        if (savedInstanceState == null) {
            viewModel.init();
        }
        binding.setMainViewModel(viewModel);

        if (isNetworkConnected()) {
            populateAllWonders();
        } else
            showToast(getString(R.string.no_network));
    }

    private void populateAllWonders() {
        viewModel.getMutableAllWonders().observe(this, new Observer<List<WondersResponse.DataWonders>>() {
            @Override
            public void onChanged(List<WondersResponse.DataWonders> dataWonders) {
                if (dataWonders.size() == 0) {
                } else {
                    viewModel.setWondersInAdapter(dataWonders);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_logout:
                SharedPrefUtils.clear(this);
                goTOLogin();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
