package io.smallant.wizard.ui.features.house

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.smallant.wizard.R
import io.smallant.wizard.databinding.ActivityHouseBinding
import io.smallant.wizard.ui.base.BaseActivity
import io.smallant.wizard.utils.HOUSE_ID
import io.smallant.wizard.utils.HOUSE_NAME

class HouseActivity : BaseActivity<ActivityHouseBinding, HouseViewModel>() {

    override fun getLayoutId(): Int = R.layout.activity_house
    override fun getViewModel(): HouseViewModel = ViewModelProviders.of(this).get(HouseViewModel::class.java)
    override fun getRootLayout() = findViewById<View>(R.id.rootLayout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding?.apply {
            viewModel = this@HouseActivity.getViewModel()
            setLifecycleOwner(this@HouseActivity)
        }

        var houseName: String? = getString(R.string.howgwart)
        var houseId: Int = 0

        intent?.extras?.let { bundle ->
            houseId = bundle.getInt(HOUSE_ID, 0)
            houseName = bundle.getString(HOUSE_NAME)
        }

        with(supportActionBar) {
            this?.title = houseName
            this?.setDisplayHomeAsUpEnabled(true)
        }

        if (savedInstanceState == null) {
            getViewModel().fetchData(houseId)
        }

        getViewModel().wizards.observe(this, Observer { wizards ->
            Log.d("HouseLog", "wizards = ${wizards.size}")
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}