package com.ocuvera.rtsplibrary

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment
import com.google.android.material.progressindicator.CircularProgressIndicator

/**
 * A custom dialog box that extends the DialogFragment class.
 *
 * This dialog allows you to display information with optional buttons, a customizable title,
 * and provides the ability to listen to button click events.
 *
 * @param title The title of the dialog.
 * @param info The information to display in the dialog (optional).
 * @param buttonMode The mode for displaying buttons (default is InfoDialogButtonMode.Both).
 * @param nameBtnCancel The text for the cancel button (optional).
 * @param nameBtnConfirm The text for the confirm button (optional).
 * @param btnListener The listener for button click events (optional).
 * @param isCancelableOnTouchOutside Whether the dialog is cancelable on touch outside (default is true).
 */
class MyDialogBox(
    private val title: String,
    private val info: String? = null,
    private val buttonMode: InfoDialogButtonMode = InfoDialogButtonMode.Both,
    private val nameBtnCancel: String? = null,
    private val nameBtnConfirm: String? = null,
    private val btnListener: ButtonClickListener? = null,
    private val isCancelableOnTouchOutside: Boolean = true,
): DialogFragment() {

    private lateinit var ivProgress: CircularProgressIndicator
    private lateinit var tvTitle: TextView
    private lateinit var tvInfo: TextView
    private lateinit var btnCancel: TextView
    private lateinit var btnConfirm: TextView
    private lateinit var border: View
    private lateinit var llButtons: LinearLayout

    /**
     * This constructor is to prevent random crashes that ART throws while serilazing/deserializing this class.
     */
    constructor() : this("") {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.my_dialog_box, container, false)
        ivProgress = view.findViewById(R.id.progress_circular)
        tvTitle = view.findViewById(R.id.tv_title)
        tvInfo = view.findViewById(R.id.tv_info)
        ivProgress = view.findViewById(R.id.progress_circular)
        btnCancel = view.findViewById(R.id.btn_cancel)
        btnConfirm = view.findViewById(R.id.btn_confirm)
        border = view.findViewById(R.id.border_btn)
        llButtons = view.findViewById(R.id.ll_buttons)

        dialog?.window?.setBackgroundDrawable(ResourcesCompat.getDrawable(resources, R.drawable.bg_my_dialog_box, null))
        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val alertD = builder.create()
        alertD.setView(view)
        alertD.create()
        alertD.window!!.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        )
        alertD.setCancelable(isCancelable)
        alertD.setCanceledOnTouchOutside(isCancelable)
        alertD.show()

        /**
         * To hide system bottom navigation, you can customize to your needs or remove.
         */
//        val windowInsetsController = WindowCompat.getInsetsController(alertD.window!!, alertD.window!!.decorView)
//        alertD.window!!.decorView.setOnApplyWindowInsetsListener { v, insets ->
//            if (windowInsetsController != null) {
//                windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//                alertD.window!!.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//            }
//            v.onApplyWindowInsets(insets)
//        }
        alertD.window!!.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)

        return alertD
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * Set [title] to TextView [tvTitle].
         */
        tvTitle.text = title
        /**
         * If [info] is not null or empty, set TextView [tvInfo].
         */
        if (!info.isNullOrBlank()) {
            tvInfo.apply {
                visibility = View.VISIBLE
                text = info
            }
        }
        /**
         * Update [btnCancel] and [btnConfirm] buttons according to [buttonMode].
         */
        when(buttonMode) {
            /**
             * Hide both buttons.
             */
            InfoDialogButtonMode.None -> {
                llButtons.visibility = View.GONE
            }
            /**
             * Show only [btnCancel] button.
             */
            InfoDialogButtonMode.Cancel -> {
                /**
                 * Hide [border] between buttons and [btnConfirm].
                 */
                border.visibility = View.GONE
                btnConfirm.visibility = View.GONE
                /**
                 * Show [btnCancel].
                 */
                showButtonCancel()
            }
            /**
             * Show only [btnConfirm] button.
             */
            InfoDialogButtonMode.Confirm -> {
                /**
                 * Hide [border] between buttons and [btnCancel].
                 */
                border.visibility = View.GONE
                btnCancel.visibility = View.GONE
                /**
                 * Show [btnConfirm].
                 */
                showButtonConfirm()
            }
            /**
             * Hide both [btnCancel] and [btnConfirm] buttons.
             */
            InfoDialogButtonMode.Both -> {
                llButtons.visibility = View.VISIBLE
                showButtonCancel()
                showButtonConfirm()
            }
        }
    }

    /**
     * Shows [btnConfirm], set onClickListener and name of the button.
     */
    private fun showButtonConfirm() {
        btnConfirm.apply {
            visibility = View.VISIBLE
            setOnClickListener {
                dismissAllowingStateLoss() // Dismiss this DialogFragment.
                btnListener?.onBtnConfirmClick()
            }
        }
        nameBtnConfirm?.let {
            btnConfirm.text = it
        }
    }

    /**
     * Shows [btnCancel], set onClickListener and name of the button.
     */
    private fun showButtonCancel() {
        btnCancel.apply {
            visibility = View.VISIBLE
            setOnClickListener {
                dismissAllowingStateLoss() // Dismiss this DialogFragment.
                btnListener?.onBtnCancelClick()
            }
        }
        nameBtnCancel.let {
            btnCancel.text = it
        }
    }

    enum class InfoDialogButtonMode {
        None, Cancel, Confirm, Both
    }

    interface ButtonClickListener {
        fun onBtnCancelClick()
        fun onBtnConfirmClick()
    }
}