package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.User;
import utils.DBUtil;

public class UserValidator {
    public static List<String> validate(User u, Boolean password_check_flag, Boolean gender_check_flag, Boolean age_check_flag) {
        List<String> errors = new ArrayList<String>();

        String name_error = _validateName(u.getName());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        String password_error = _validatePassword(u.getPassword());
        if(!password_error.equals("")) {
            errors.add(password_error);
        }
        
        String gender_error = _validateGender(u.getGender());
        if(!gender_error.equals("")){
        	errors.add(gender_error);
        }
        
        String age_error = _validateAge(u.getAge());
        if(!age_error.equals("")){
        	errors.add(age_error);
        }
        
        return errors;
    }

    private static String _validateName(String name) {
        if(name == null || name.equals("")) {
            return "氏名を入力してください。";
        }else {
            EntityManager em = DBUtil.createEntityManager();
            
			long jcount = (long)em.createNamedQuery("checkNameJuhuku", Long.class)
					  .setParameter("juhukuname", name)
					  .getSingleResult();
			
            em.close();
            if(jcount > 0) {
                return "入力されたユーザー名はすでに存在しています。";
            }
        }
        return "";
    }

    private static String _validatePassword(String password) {
        if(password == null || password.equals("")) {
            return "パスワードを入力してください。";
        }
        return "";
    }
    
    private static String _validateGender(int gender) {
        if(gender == 0) {
            return "性別を選択してください";
        }
        return "";
    }
    
    private static String _validateAge(int age) {
        if(age == 0) {
            return "年齢を選択してください。";
        }
        return "";
    }
    
}