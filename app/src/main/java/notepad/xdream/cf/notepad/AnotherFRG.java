package notepad.xdream.cf.notepad;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import notepad.xdream.cf.notepad.bean.Notes;
import notepad.xdream.cf.notepad.tools.BackHandledFragment;

/**
 * Created by kwinter on 2017/5/25.
 */

public class AnotherFRG extends BackHandledFragment{
    EditText input=null;
    View root=null;
    Notes notes=null;
    public AnotherFRG(){}
   public static AnotherFRG newInstance(Notes notes) {
       AnotherFRG fragment = new AnotherFRG();
       Bundle args = new Bundle();
       args.putString("content",notes.getContent());
       args.putInt("id",notes.getId());
       fragment.setArguments(args);
       return fragment;
   }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         root=inflater.inflate(R.layout.add_notes,container,false);
         input=(EditText) root.findViewById(R.id.inputNotes);
        if (getArguments() != null) {
            String content = getArguments().getString("content");
            int id=getArguments().getInt("id");
             notes=new Notes();
            notes.setId(id);
            input.setText(content);
            //notes.setContent(content);
            //notes.setDate(new Date());
        }
        root.findViewById(R.id.changeA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.container,new BlankFragment()).commit();
            }
        });
        return root;

    }

    @Override
    public boolean onBackPressed() {
       if(notes==null)
           notes=new Notes();
        notes.setContent(input.getText().toString());
        notes.setDate(new Date());
        if (notes.save()) {
            Toast.makeText(root.getContext(), "存储成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(root.getContext(), "存储失败", Toast.LENGTH_SHORT).show();
        }
        getFragmentManager().beginTransaction().replace(R.id.container,new BlankFragment() ).commit();
        return true;
    }
}
