package edu.nps.moves.dis7;

import java.util.*;
import java.io.*;


/**
 * the variable datum type, the datum length, and the value for that variable datum type. NOT COMPLETE. Section 6.2.93
 *
 * Copyright (c) 2008-2016, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class VariableDatum extends Object implements Serializable
{
   /** ID of variable datum to be transmitted. 32 bit enumeration defined in EBV */
   protected long  variableDatumID;

   /** Length, IN BITS, of the variable datum. */
   protected long  variableDatumLength;

   /** Variable length data class */
   protected byte[] variableDatumData = new byte[0];

/** Constructor */
 public VariableDatum()
 {
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 4;  // variableDatumID
   marshalSize = marshalSize + 4;  // variableDatumLength
   marshalSize = marshalSize + variableDatumData.length;

   return marshalSize;
}


public void setVariableDatumID(long pVariableDatumID)
{ variableDatumID = pVariableDatumID;
}

public long getVariableDatumID()
{ return variableDatumID; 
}

public long getVariableDatumLength()
{ return (long)variableDatumData.length;
}

/** Note that setting this value will not change the marshalled value. The list whose length this describes is used for that purpose.
 * The getvariableDatumLength method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setVariableDatumLength(long pVariableDatumLength)
{ variableDatumLength = pVariableDatumLength;
}

public void setVariableDatumData(byte[] pVariableDatumData)
{ variableDatumData = pVariableDatumData;
}

public byte[] getVariableDatumData()
{ return variableDatumData; }


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeInt( (int)variableDatumID);
       dos.writeInt( (int)variableDatumData.length);
       dos.write(variableDatumData);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       variableDatumID = dis.readInt();
       variableDatumLength = dis.readInt();
       variableDatumData = new byte[(int) variableDatumLength];
       dis.read(variableDatumData);
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


/**
 * Packs a Pdu into the ByteBuffer.
 * @throws java.nio.BufferOverflowException if buff is too small
 * @throws java.nio.ReadOnlyBufferException if buff is read only
 * @see java.nio.ByteBuffer
 * @param buff The ByteBuffer at the position to begin writing
 * @since ??
 */
public void marshal(java.nio.ByteBuffer buff)
{
       buff.putInt( (int)variableDatumID);
       buff.putInt( (int)variableDatumData.length);
       buff.put(variableDatumData);
    } // end of marshal method

/**
 * Unpacks a Pdu from the underlying data.
 * @throws java.nio.BufferUnderflowException if buff is too small
 * @see java.nio.ByteBuffer
 * @param buff The ByteBuffer at the position to begin reading
 * @since ??
 */
public void unmarshal(java.nio.ByteBuffer buff)
{
       variableDatumID = buff.getInt();
       variableDatumLength = buff.getInt();
       variableDatumData = new byte[(int) variableDatumLength];
       buff.get(variableDatumData);
 } // end of unmarshal method 


 /*
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
  */
@Override
 public boolean equals(Object obj)
 {

    if(this == obj){
      return true;
    }

    if(obj == null){
       return false;
    }

    if(getClass() != obj.getClass())
        return false;

    return equalsImpl(obj);
 }

 /**
  * Compare all fields that contribute to the state, ignoring
 transient and static fields, for <code>this</code> and the supplied object
  * @param obj the object to compare to
  * @return true if the objects are equal, false otherwise.
  */
 public boolean equalsImpl(Object obj)
 {
     boolean ivarsEqual = true;

    if(!(obj instanceof VariableDatum))
        return false;

     final VariableDatum rhs = (VariableDatum)obj;

     if( ! (variableDatumID == rhs.variableDatumID)) ivarsEqual = false;
     if( ! (variableDatumLength == rhs.variableDatumLength)) ivarsEqual = false;
     if( ! (Arrays.equals(variableDatumData, rhs.variableDatumData))) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
