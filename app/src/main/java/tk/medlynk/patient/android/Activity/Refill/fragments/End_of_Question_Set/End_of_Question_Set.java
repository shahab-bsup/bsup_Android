package tk.medlynk.patient.android.Activity.Refill.fragments.End_of_Question_Set;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neweraandroid.demo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link End_of_Question_Set.OnNewSymptomTwenty4QuestionListener} interface
 * to handle interaction events.
 * Use the {@link End_of_Question_Set#newInstance} factory method to
 * create an instance of this fragment.
 */
public class End_of_Question_Set extends Fragment implements End_of_Question_Set_VH.OnEndOfQuestionSetVHListener {

    public static final String TAG = End_of_Question_Set.class.getSimpleName ();

    private OnNewSymptomTwenty4QuestionListener mListener;

    private View view;
    private End_of_Question_Set_VH viewHolder;

    public End_of_Question_Set() {
        // Required empty public constructor
    }

    public static End_of_Question_Set newInstance() {
        End_of_Question_Set fragment = new End_of_Question_Set ();
        Bundle args = new Bundle ();
        fragment.setArguments ( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        if (getArguments () != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate ( R.layout.fragment_refill_end, container, false );
        viewHolder = new End_of_Question_Set_VH ( view );
        viewHolder.setOnEndOfQuestionSetVHListener ( this );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnNewSymptomTwenty4QuestionListener) {
            mListener = (OnNewSymptomTwenty4QuestionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnNewSymptomTwenty4QuestionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onButtonClicked(int buttonId) {
        System.out.println ( "End_of_Question_Set.onButtonClicked" );
        switch (buttonId) {
            case 0: {


                break;
            }
            case 1: {


                break;
            }
            case 2: {


                break;
            }
            case 3: {


                break;
            }
            case 4: {

                break;
            }
        }
    }

    public interface OnNewSymptomTwenty4QuestionListener {
        // TODO: Update argument type and name
        void onTwenty4Question();
    }
}
