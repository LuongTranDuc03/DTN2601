package com.vti.entity;

public class VietnamesePhone extends Phone {

    @Override
    public void insertContact(String name, String phone) {
        boolean exists = false;
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                exists = true;
                break;
            }
        }
        if (exists) {
            System.out.println("Contact with name '" + name + "' already exists.");
        } else {
            contacts.add(new Contact(name, phone));
            System.out.println("Inserted: " + name + " - " + phone);
        }
    }

    @Override
    public void removeContact(String name) {
        boolean removed = contacts.removeIf(contact -> contact.getName().equals(name));
        if (removed) {
            System.out.println("Removed contact: " + name);
        } else {
            System.out.println("Contact not found: " + name);
        }
    }

    @Override
    public void updateContact(String name, String newPhone) {
        boolean updated = false;
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                contact.setPhone(newPhone);
                updated = true;
                System.out.println("Updated contact '" + name + "' with new phone: " + newPhone);
                break;
            }
        }
        if (!updated) {
            System.out.println("Contact not found: " + name);
        }
    }

    @Override
    public void searchContact(String name) {
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                System.out.println("Found: " + contact);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No contact found with name: " + name);
        }
    }
}
