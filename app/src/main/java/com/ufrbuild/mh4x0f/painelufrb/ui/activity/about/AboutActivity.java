/*
    This file is part of the Painel de Aulas UFRB Open Source Project.
    Painel de Aulas UFRB is licensed under the Apache 2.0.

    Copyright 2019 UFRBuild - Marcos Bomfim

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */


package com.ufrbuild.mh4x0f.painelufrb.ui.activity.about;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ufrbuild.mh4x0f.painelufrb.R;
import com.ufrbuild.mh4x0f.painelufrb.data.DataManager;
import com.ufrbuild.mh4x0f.painelufrb.ui.activity.about.adapters.ContributorAdapter;
import com.ufrbuild.mh4x0f.painelufrb.ui.activity.about.adapters.DevelopersAdapter;
import com.ufrbuild.mh4x0f.painelufrb.ui.activity.about.adapters.LicenseAdapter;
import com.ufrbuild.mh4x0f.painelufrb.ui.activity.about.adapters.LinksAdapter;
import com.ufrbuild.mh4x0f.painelufrb.ui.activity.about.models.Contributor;
import com.ufrbuild.mh4x0f.painelufrb.ui.activity.about.models.Developer;
import com.ufrbuild.mh4x0f.painelufrb.ui.activity.about.models.License;
import com.ufrbuild.mh4x0f.painelufrb.ui.activity.about.models.Links;
import com.ufrbuild.mh4x0f.painelufrb.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class AboutActivity extends AppCompatActivity implements
        DevelopersAdapter.OnDevelopersAdapter,
        ContributorAdapter.OnContributorAdapter,
        LinksAdapter.OnLinksAdapter, LicenseAdapter.OnLicenseAdapter{
    private Toolbar mToolbar;
    DataManager mDataManager;
    @BindView(R.id.rv_developers)
    RecyclerView rv_developers;
    @BindView(R.id.rv_contributors) RecyclerView rv_contributors;
    @BindView(R.id.rv_links) RecyclerView rv_links;
    @BindView(R.id.rv_licenses) RecyclerView rv_licenses;
    DevelopersAdapter mDevelopersAdapter;
    ContributorAdapter mContributorsAdapter;
    LinksAdapter mLinksAdapter;
    LicenseAdapter mLicensesAdapter;
    LinearLayoutManager manager_developers;
    LinearLayoutManager manager_contributors;
    LinearLayoutManager manager_links;
    LinearLayoutManager manager_licenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDataManager = DataManager.getInstance();
        if(mDataManager.getPrefs().getBoolean(getString(R.string.theme_key))) {
            setTheme(R.style.Darktheme);
        }
        else  setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        mToolbar = findViewById(R.id.main_appbar_about);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.action_title_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CommonUtils.getSupportActionBar(this);


        ButterKnife.bind(this);

        mDevelopersAdapter = new DevelopersAdapter(this);
        rv_developers.setAdapter(mDevelopersAdapter);
        manager_developers = new LinearLayoutManager(this);
        rv_developers.setLayoutManager(manager_developers);


        mLinksAdapter = new LinksAdapter(this);
        rv_links.setAdapter(mLinksAdapter);
        manager_links = new LinearLayoutManager(this);
        rv_links.setLayoutManager(manager_links);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv_links.getContext(),LinearLayoutManager.VERTICAL);
        rv_links.addItemDecoration(dividerItemDecoration);

        mContributorsAdapter = new ContributorAdapter(this);
        manager_contributors = new LinearLayoutManager(this);
        rv_contributors.setAdapter(mContributorsAdapter);
        rv_contributors.setLayoutManager(manager_contributors);

        mLicensesAdapter = new LicenseAdapter(this);
        manager_licenses = new LinearLayoutManager(this);
        rv_licenses.setAdapter(mLicensesAdapter);
        rv_licenses.setLayoutManager(manager_licenses);
        DividerItemDecoration divider_licenses = new DividerItemDecoration(rv_licenses.getContext(),LinearLayoutManager.VERTICAL);
        rv_licenses.addItemDecoration(divider_licenses);


        mDevelopersAdapter.setItems(getAllDevelopers());
        mContributorsAdapter.setItems(getAllContributors());
        mLinksAdapter.setItems(getAllLinks());
        mLicensesAdapter.setItems(getAllLicenses());


    }

    public List<Developer> getAllDevelopers(){
        ArrayList<Developer> developers =  new ArrayList<Developer>();
        developers.add(new Developer(getString(R.string.developer_name),
                getString(R.string.photo_perfil_developer),
                getString(R.string.instagram_develper), getString(R.string.github_developer)));
        return developers;
    }

    public List<Links> getAllLinks(){
        ArrayList<Links> links =  new ArrayList<Links>();
        links.add(new Links("Ver código-fonte (OpenSource)", getString(R.string.github_repository), "@drawable/ic_favorite_black_24dp"));
        links.add(new Links("Avaliar esse app", "", "@drawable/ic_favorite_black_24dp"));
        links.add(new Links("Licença Apache 2.0", getString(R.string.github_license), "@drawable/ic_favorite_black_24dp"));
        links.add(new Links("Política de Privacidade", getString(R.string.github_policy), "@drawable/ic_favorite_black_24dp"));
        return links;
    }

    public List<Contributor> getAllContributors(){
        ArrayList<Contributor> contributors =  new ArrayList<Contributor>();
        contributors.add(new Contributor("Marcos Silva", getString(R.string.photo_perfil_marcos_contributor),"https://github.com/mcilva"));
        contributors.add(new Contributor("Tassio Vale", getString(R.string.photo_perfil_tassio_vale) ,"https://github.com/tassiovale"));
        return contributors;
    }

    public List<License> getAllLicenses(){
        ArrayList<License> licenses =  new ArrayList<License>();
        licenses.add(new License("butterknife",
                "JakeWharton", "Bind Android views and callbacks to fields and methods.",
                "https://github.com/JakeWharton/butterknife"));
        licenses.add(new License("Glide",
                "glide", "An image loading and caching library " +
                "for android focused on smooth scrolling. (Multi licenses)",
                "https://github.com/bumptech/glide"));
        licenses.add(new License("MaterialDrawer",
                "mikepenz", "The flexible, easy to use, all in one drawer library for your Android project",
                "https://github.com/mikepenz/MaterialDrawer"));
        licenses.add(new License("Android-Iconics",
                "mikepenz", "Use any icon font, or vector (.svg) as drawable in your application.",
                "https://github.com/mikepenz/Android-Iconics"));

        licenses.add(new License("circular-with-floating-action-button",
                "DmitryMalkovich", "Circular progress with fab integration ",
                "https://github.com/DmitryMalkovich/circular-with-floating-action-button"));


        licenses.add(new License("PowerPreference",
                "AliAsadi", "A Powerful library to control and simplify the usage of shared preference in Android.",
                "https://github.com/AliAsadi/PowerPreference"));

        licenses.add(new License("retrofit 2",
                "square", "Type-safe HTTP client for Android and Java by Square, Inc.",
                "https://github.com/square/retrofit"));

        licenses.add(new License("floatingsearchview",
                "arimorty", "A search view that implements a floating search bar also known as persistent search",
                "https://github.com/arimorty/floatingsearchview"));

        licenses.add(new License("search-dialog",
                "mirrajabi", "A fast and customizable search dialog ",
                "https://github.com/mirrajabi/search-dialog"));

        licenses.add(new License("gson",
                "google", "A Java serialization/deserialization library to convert Java Objects into JSON and back",
                "https://github.com/google/gson"));

        licenses.add(new License("Freepik",
                "Freepik", "The icon logo this project",
                "https://www.flaticon.com/free-icon/computer_1846883"));

        return licenses;
    }

    // https://stackoverflow.com/questions/10816757/rate-this-app-link-in-google-play-store-app-on-the-phone
    public void callIntentAvaliarApp() {
        Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.url_base_googleplay_app) +  getApplicationContext().getPackageName())));
        }
    }

    @Override
    public void onDevelopersClicked(Developer discipline) {

    }

    @Override
    public void onContributorClicked(Contributor contributor) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(contributor.getGithub()));
        startActivity(browserIntent);
    }

    public void callIntentShareApp(View view) {
    }

    @Override
    public void onLinksClicked(Links link) {
        if (link.getUrl() == ""){
            callIntentAvaliarApp();
            return;
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link.getUrl()));
        startActivity(browserIntent);
    }

    @Override
    public void onLicenseClicked(License license) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(license.getUrl()));
        startActivity(browserIntent);
    }
}