package com.yuseok.android.runtimepermission;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;

/**
 * Created by YS on 2017-02-01.
 */

public class DataLoader {

    private ArrayList<Contact> datas = new ArrayList<>();
    // Context의 기능
    // 어플리케이션에 관하여 시스템이 관리하고 있는 정보에 접근하기
    // 안드로이드 시스템 서비스에서 제공하는 API 를 호출 할 수 있는 기능
    private Context context;


    public DataLoader(Context context) {
        this.context = context;
    }



    public ArrayList<Contact> get(){
        return datas;
    }

    public void load(){
        //1. 주소록에 접근하기 위해 ContentResolver를 불러온다.
        // 단말기에서 공용으로 사용되는 데이터를 CURD하기 위해 ContentResolver사용
        ContentResolver resolver = context.getContentResolver();

        // 2. 주소록에서 가져올 데이터 컬럼명을 정의한다.
        // projections라는 컬럼에 데이터의 아이디, 이름, 전화번호를 가져와서 담는다.
        String projections[] = {
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID, // 데이터의 아이디
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, // 이름
                ContactsContract.CommonDataKinds.Phone.NUMBER   // 전화번호
        };

        // 3. cintent Resolver로 쿼리한 데이터를 커서에 담는다
        // 전화번호 URI : ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        // 주소록 URI : ContactsContract.Contacts.CONTENT_URI
        //              HAS_PHONE_NUMBER : 전화번호가 있는지 확인하는 상수
        Cursor cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI //데이터의 주소
                    , projections          // 가져올 데이터 컬럼명 배열
                    , null                  // 조건절에 들어가는 컬럼명들 지정
                    , null                  // 지정된 컬럼명과 메핑되는 실제 조건 값
                    , null                   // 정렬
        );

        if(cursor != null) {
            // 4. 커서에 넘어온 데이터가 있다면 반복문을 돌면서 datas에 담아준다.
            while (cursor.moveToNext()) {
                Contact contact = new Contact();
                // 5. 커서의 컬럼 인덱스를 가져온 후
                int idx = cursor.getColumnIndex(projections[0]);
                // 5.1 컬럼 인덱스에 해당하는 타입에 맞게 값을 꺼내서 셋팅한다.
                contact.setId(cursor.getInt(idx)); // 0번째를 int로 꺼내겠다.

                idx = cursor.getColumnIndex(projections[1]);
                contact.setName(cursor.getString(idx));

                idx = cursor.getColumnIndex(projections[2]);
                contact.addTel(cursor.getString(idx));

                datas.add(contact);
            }
            // * 중요 : 사용 후 close를 호출하지 않으면 메모리 누수가 발생할 수 있다.
            cursor.close();
        }
    }
}
