/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.promoapp.ui

import io.branch.indexing.BranchUniversalObject
import io.branch.referral.Branch
import io.branch.referral.BranchError
import io.branch.referral.util.LinkProperties

import android.util.Log;
import org.json.JSONObject;


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.raywenderlich.android.promoapp.R
import com.raywenderlich.android.promoapp.databinding.ActivityPromoBinding
import io.branch.referral.util.CurrencyType

import io.branch.referral.util.BRANCH_STANDARD_EVENT

import io.branch.referral.util.BranchEvent

import io.branch.referral.util.BranchContentSchema

import io.branch.referral.util.ContentMetadata

import io.branch.referral.util.ProductCategory







class LauncherActivity : AppCompatActivity() {
  private lateinit var activityPromoBinding: ActivityPromoBinding
  var count = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    setTheme(R.style.AppTheme)
    super.onCreate(savedInstanceState)
    activityPromoBinding = ActivityPromoBinding.inflate(layoutInflater)
    setContentView(activityPromoBinding.root)

    handleIntent(intent)

  }

  override fun onStart() {
    super.onStart()
    // Branch init
    Branch.sessionBuilder(this).withCallback(branchListener).withData(this.intent?.data).init()
  }

  object branchListener : Branch.BranchReferralInitListener {
    override fun onInitFinished(referringParams: JSONObject?, error: BranchError?) {
      if (error == null) {
        Log.i("BRANCH SDK", referringParams.toString())
        // Retrieve deeplink keys from 'referringParams' and evaluate the values to determine where to route the user
        // Check '+clicked_branch_link' before deciding whether to use your Branch routing logic
      } else {
        Log.e("BRANCH SDK", error.message)
      }
    }
  }


  private fun handleIntent(intent: Intent?) {
    val appLinkAction: String? = intent?.action
    val appLinkData: Uri? = intent?.data
    showDeepLinkOffer(appLinkAction, appLinkData)
  }

  override fun onNewIntent(intent: Intent) {
    super.onNewIntent(intent)
    this.intent = intent
    // Branch reinit (in case Activity is already in foreground when Branch link is clicked)
    Branch.sessionBuilder(this).withCallback(branchListener).reInit()
  }

  private fun showDeepLinkOffer(appLinkAction: String?, appLinkData: Uri?) {
    // 1
    if (Intent.ACTION_VIEW == appLinkAction && appLinkData != null) {
      // 2
      val promotionCode = appLinkData.getQueryParameter("code")
      if (promotionCode.isNullOrBlank().not()) {
        activityPromoBinding.discountGroup.visibility = View.VISIBLE
        activityPromoBinding.tvPromoCode.text = promotionCode
        // 3
        activityPromoBinding.btnClaimOffer.setOnClickListener {
          activityPromoBinding.tvOfferClaimed.visibility = View.VISIBLE
        }
      } else {
        activityPromoBinding.discountGroup.visibility = View.GONE
      }
    }
  }


  // event tracking: commerce
  fun commerceEvent(view: View) {

    // increment count
        count++

    // locate textView and update the label
        val textview = findViewById(R.id.textView) as TextView
        textview.text = "You clicked $count times."

        // commerce event tracking (Branch SDK guide)
        val buo = BranchUniversalObject()
          .setCanonicalIdentifier("myprod/1234")
          .setCanonicalUrl("https://test_canonical_url")
          .setTitle("test_title")
          .setContentMetadata(
            ContentMetadata()
              .addCustomMetadata("custom_metadata_key1", "custom_metadata_val1")
              .addCustomMetadata("custom_metadata_key1", "custom_metadata_val1")
              .addImageCaptions("image_caption_1", "image_caption2", "image_caption3")
              .setAddress("Street_Name", "test city", "test_state", "test_country", "test_postal_code")
              .setRating(5.2, 6.0, 5)
              .setLocation(-151.67, -124.0)
              .setPrice(10.0, CurrencyType.USD)
              .setProductBrand("test_prod_brand")
              .setProductCategory(ProductCategory.APPAREL_AND_ACCESSORIES)
              .setProductName("test_prod_name")
              .setProductCondition(ContentMetadata.CONDITION.EXCELLENT)
              .setProductVariant("test_prod_variant")
              .setQuantity(1.5)
              .setSku("test_sku")
              .setContentSchema(BranchContentSchema.COMMERCE_PRODUCT)
          )
        .addKeyWord("keyword1")
        .addKeyWord("keyword2")


        //  Do not add an empty branchUniversalObject to the BranchEvent
            BranchEvent(BRANCH_STANDARD_EVENT.ADD_TO_CART)
              .setAffiliation("test_affiliation")
              .setCustomerEventAlias("my_custom_alias")
              .setCoupon("Coupon Code")
              .setCurrency(CurrencyType.USD)
              .setDescription("Customer added item to cart")
              .setShipping(0.0)
              .setTax(9.75)
              .setRevenue(1.5)
              .setSearchQuery("Test Search query")
              .addCustomDataProperty("Custom_Event_Property_Key1", "Custom_Event_Property_val1")
              .addCustomDataProperty("Custom_Event_Property_Key2", "Custom_Event_Property_val2")
              .addContentItems(buo)
              .logEvent(applicationContext)
  }

  // event tracking: content
  fun contentEvent(view: View) {

    // increment count
    count++

    // locate textView and update the label
    val textview = findViewById(R.id.textView) as TextView
    textview.text = "You clicked $count times."

    // content event tracking (Branch SDK guide)
    BranchEvent(BRANCH_STANDARD_EVENT.SEARCH)
      .setCustomerEventAlias("my_custom_alias")
      .setDescription("Product Search")
      .setSearchQuery("product name")
      .addCustomDataProperty("Custom_Event_Property_Key1", "Custom_Event_Property_val1")
      .logEvent(applicationContext);
  }

  // event tracking: lifecycle
  fun lifecycleEvent(view: View) {

    // increment count
    count++

    // locate textView and update the label
    val textview = findViewById(R.id.textView) as TextView
    textview.text = "You clicked $count times."

    // lifecycle event tracking (Branch SDK guide)
    BranchEvent(BRANCH_STANDARD_EVENT.COMPLETE_REGISTRATION)
      .setCustomerEventAlias("my_custom_alias")
      .setTransactionID("tx1234")
      .setDescription("User created an account")
      .addCustomDataProperty("registrationID", "12345")
      .logEvent(applicationContext);
  }

  // event tracking: custom
  fun customEvent(view: View) {

    // increment count
    count++

    // locate textView and update the label
    val textview = findViewById(R.id.textView) as TextView
    textview.text = "You clicked $count times."

    // custom event tracking (Branch SDK guide)
    BranchEvent("Some Custom Event")
      .addCustomDataProperty("Custom_Event_Property_Key11", "Custom_Event_Property_val11")
      .addCustomDataProperty("Custom_Event_Property_Key22", "Custom_Event_Property_val22")
      .setCustomerEventAlias("my_custom_alias")
      .logEvent(this);

  }

}