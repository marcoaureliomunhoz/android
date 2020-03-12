package br.com.marcoaureliomunhoz.memolist.activities;
import br.com.marcoaureliomunhoz.memolist.MemoListApplication;
import br.com.marcoaureliomunhoz.memolist.R;
import br.com.marcoaureliomunhoz.memolist.helpers.IntentHelper;
import br.com.marcoaureliomunhoz.memolist.helpers.ToastHelper;
import br.com.marcoaureliomunhoz.memolist.models.Record;
import br.com.marcoaureliomunhoz.memolist.repositories.RepositoryTemplate;
import butterknife.BindView;
import butterknife.ButterKnife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.List;

public class MemoFormActivity extends AppCompatActivity {

    public static final String EXTRA_MODEL_NAME = "model";

    @BindView(R.id.txtTitleMemoForm) EditText txtTitle;
    @BindView(R.id.txtDescriptionMemoForm) EditText txtDescription;
    @BindView(R.id.txtResponsibleMemoForm) EditText txtResponsible;

    private final RepositoryTemplate<Record> repository = MemoListApplication.repository;
    private Record model = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_form);

        ButterKnife.bind(this);

        boolean dataLoaded = loadModelFromExtra();

        if (dataLoaded)
            setTitle(getString(R.string.edit_item));
        else
            setTitle(getString(R.string.new_item));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.memo_form_actitivity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_save_record) {
            save();
        }
        return super.onOptionsItemSelected(item);
    }

    private void save() {
        loadModelFromView();
        int confirmation = repository.save(model);
        if (confirmation >= 0) {
            Intent intent = new Intent();
            intent.putExtra(MemoListActivity.EXTRA_RESULT_MODEL_NAME, model);
            setResult(Activity.RESULT_OK, intent);
            finish();
        } else {
            List<String> requirements = model.getRequirements();
            if (requirements.size() > 0) {
                ToastHelper.show(this, requirements.get(0));
            }
        }
    }

    private boolean loadModelFromExtra() {
        model = (Record) IntentHelper.getSerializable(this, MemoFormActivity.EXTRA_MODEL_NAME);

        if (model != null) {
            txtTitle.setText(model.getTitle());
            txtDescription.setText(model.getDescription());
            txtResponsible.setText(model.getResponsible());
            return true;
        }

        return false;
    }

    private void loadModelFromView() {
        String title = txtTitle.getText().toString();
        String description = txtDescription.getText().toString();
        String responsible = txtResponsible.getText().toString();

        if (model == null) {
            model = new Record(title, description);
        } else {
            model.setTitle(title);
            model.setDescription(description);
            model.setResponsible(responsible);
        }
    }
}
