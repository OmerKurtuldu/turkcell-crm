package com.turkcell.customerService.mernis;

//------------------------------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 9.0.0.0
//
// Created by Quasar Development 
//
// This class has been generated for test purposes only and cannot be used in any commercial project.
// To use it in commercial project, you need to generate this class again with Premium account.
// Check https://EasyWsdl.com/Payment/PremiumAccountDetails to see all benefits of Premium account.
//
// Licence: 6BDA7307AB9A9D9C44386310DB379544EECBD8DC205E2A0B4E5098863585B49D4CB1A6A2F8E3258D48D0E86B5554655798063A81136C904493D40D91B7E49917
//------------------------------------------------------------------------

import org.ksoap2.serialization.Marshal;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.util.UUID;


public class VFNMarshalGuid implements Marshal
{
    public java.lang.Object readInstance(XmlPullParser parser, java.lang.String namespace, java.lang.String name,PropertyInfo expected) throws IOException, XmlPullParserException
    {
        return UUID.fromString(parser.nextText());
    }

    public void register(SoapSerializationEnvelope cm)
    {
        cm.addMapping(cm.xsd, "guid", UUID.class, this);
    }

    public void writeInstance(XmlSerializer writer, java.lang.Object obj) throws IOException
    {
        writer.text(obj.toString());
    }
}
