package tk.medlynk.patient.android.Activity.NewSymptom.fragments

import android.content.Context
import android.net.Uri
import android.nfc.Tag
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.neweraandroid.demo.R;

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [IntroFragment.OnIntroFragmentListener] interface
 * to handle interaction events.
 * Use the [IntroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IntroFragment : Fragment(), View.OnClickListener {
    override fun onClick(p0: View?) {
        mListenerIntro!!.onIntroFragmentInteraction()
    }

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private lateinit var button : Button

    private var mListenerIntro: OnIntroFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_intro, container, false)
        button = view.findViewById(R.id.btnNextQuestion) as Button
        button.setOnClickListener(this)
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListenerIntro != null) {
            mListenerIntro!!.onIntroFragmentInteraction()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnIntroFragmentListener) {
            mListenerIntro = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnIntroFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListenerIntro = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnIntroFragmentListener {
        // TODO: Update argument type and name
        fun onIntroFragmentInteraction()
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        @JvmField val TAG :String = "IntroFragment"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment IntroFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): IntroFragment {
            val fragment = IntroFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
