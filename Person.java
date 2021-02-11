/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appointmentviewer;

/**
 *
 * @author laltobel
 */
public class Person implements Comparable<Person>{
    String person;
    String lastName;
    String firstName;
    String telephone;
    String address;
    String email;
    public Person(String person,String lastName,String firstName,String telephone, String email, String address){
        this.person = person;
        this.address = address;
        this.lastName = lastName;
        this.telephone = telephone;
        this.email = email;
       }

   public Person() {
      
    }
    
    public String getlastName(){
        return lastName;
    }        
    public String getfirstName(){
        return firstName;
    }       
    public String getTelephone(){
        return telephone;
    }
    public String getAddress(){
        return address;
    }
    public String getEmail(){
        return email;
    }
    public int compareTo(Person other, Person other2){
           if(other.lastName == other2.lastName){
               if(other.firstName != other2.firstName) return 1;}
            if(other.lastName != other2.lastName) return -1; 
            return 0;
    }
    public String toString(){
        String s = firstName + lastName + telephone + address + email;
        return s;
    }
    public class Email implements Comparable<Person>{
        public Email(){
            
        }
        public int compareTo(Person other, Person other2){
            if(other.email == other2.email)return 1;
            if(other.email != other2.email)return -1;
            return 0;
        }
    }
    public class Telephone implements Comparable<Person>{
        public Telephone(){
            
        }
          public int compareTo(Person other, Person other2){
            if(other.telephone == other2.telephone)return 1;
            if(other.telephone != other2.telephone)return -1;
            return 0;
        }
    
    }
}

