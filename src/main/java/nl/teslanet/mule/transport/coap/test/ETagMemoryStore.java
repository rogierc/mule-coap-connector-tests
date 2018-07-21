/*******************************************************************************
 * Copyright (c) 2017, 2018 (teslanet.nl) Rogier Cobben.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Public License - v 2.0 which accompany this distribution.
 * 
 * The Eclipse Public License is available at
 *    http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *    (teslanet.nl) Rogier Cobben - initial creation
 ******************************************************************************/

package nl.teslanet.mule.transport.coap.test;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.concurrent.ConcurrentSkipListMap;

import nl.teslanet.mule.transport.coap.commons.options.ETag;

/**
 * Store for to keep etags in memory that need to survive multiple requests
 *
 */
public class ETagMemoryStore
{
	private static ConcurrentSkipListMap<String, ETag> etags= new  ConcurrentSkipListMap<String, ETag>();
    private static ConcurrentSkipListMap<String, String> payloads= new  ConcurrentSkipListMap<String, String>();
    private static ConcurrentSkipListMap<String, String> contentTypes= new  ConcurrentSkipListMap<String, String>();
    private static ConcurrentSkipListMap<String, String> correlationIds= new  ConcurrentSkipListMap<String, String>();
    private static ConcurrentSkipListMap<String, String> hosts= new  ConcurrentSkipListMap<String, String>();
    private static ConcurrentSkipListMap<String, String> ports= new  ConcurrentSkipListMap<String, String>();
	
	
	/**
	 * No need to create instances
	 */
	private ETagMemoryStore()
	{
		
	}
	
	/**
	 * Get the etag that belongs to a key. Create and store new etag when it doesn't exist.
	 * @param key The key of the etag.
	 * @return the etag that already existed or a newly created etag.
	 */
	public static ETag getETag( String key )
	{
		if ( ! etags.containsKey(key))
		{
			Long millis= new Date().getTime();
			ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		    buffer.putLong( millis );
		    etags.put( key, new ETag( buffer.array()));
		}
		return etags.get(key);
	}
	
	/**
	 * Set the etag that belongs to a key. 
	 * @param key The key of the etag.
	 * @param hexString The new value of the etag in hexstring format 
	 * @return the etag that already existed or a newly created etag.
	 */
	public static void setETag( String key, String hexString )
	{
		etags.put( key, new ETag( hexString ));
	}
	
	/**
	 * Create new etag for a key and store this for later use.
	 * @param key The key of the etag.
	 * @return the newly created etag.
	 */
	public static ETag getNewETag( String key )
	{
		Long millis= new Date().getTime();
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
	    buffer.putLong( millis );
	    etags.put( key, new ETag( buffer.array()));

		return etags.get(key);
	}
	
	/**
	 * Get payload that correlates to etag.
	 * @param key The key of the etag.
	 * @return the payload.
	 */
	public static String getPayload( String key )
	{
		if ( !payloads.containsKey(key))
		{
			payloads.put( key, "Payload for etag: " + etags.get(key) );
		}
		return payloads.get(key);
	}
	
	/**
	 * Set payload that correlates to etag.
	 * @param key The key of the etag.
	 * @return the payload that was set.
	 */
	public static String setPayload( String key, String payload )
	{
	    payloads.put( key, payload );

		return payload;
	}
	
	   /**
     * Get contentType that correlates to etag.
     * @param key The key of the etag.
     * @return the contentType.
     */
    public static String getContentType( String key )
    {
        if ( !contentTypes.containsKey(key))
        {
            contentTypes.put( key, "0" );
        }
        return contentTypes.get(key);
    }
    
    /**
     * Set contentType that correlates to etag.
     * @param key The key of the etag.
     * @return the contentType that was set.
     */
    public static String setContentType( String key, String contentType )
    {
        contentTypes.put( key, contentType );

        return contentType;
    }   
    
    /**
     * Get correlationId that correlates to etag.
     * @param key The key of the etag.
     * @return the correlationId.
     */
    public static String getCorrelationId( String key )
    {
        if ( !correlationIds.containsKey(key))
        {
            correlationIds.put( key, "CorrelationId for etag: " + etags.get(key) );
        }
        return correlationIds.get(key);
    }
    
    /**
     * Set correlationId that correlates to etag.
     * @param key The key of the etag.
     * @return the correlationId that was set.
     */
    public static String setCorrelationId( String key, String correlationId )
    {
        correlationIds.put( key, correlationId );

        return correlationId;
    }
    /**
     * Get host that correlates to etag.
     * @param key The key of the etag.
     * @return the host.
     */
    public static String getHost( String key )
    {
        if ( !hosts.containsKey(key))
        {
            hosts.put( key, "Host for etag: " + etags.get(key) );
        }
        return hosts.get(key);
    }
    
    /**
     * Set host that correlates to etag.
     * @param key The key of the etag.
     * @return the host that was set.
     */
    public static String setHost( String key, String host )
    {
        hosts.put( key, host );

        return host;
    }
    /**
     * Get port that correlates to etag.
     * @param key The key of the etag.
     * @return the port.
     */
    public static String getPort( String key )
    {
        if ( !ports.containsKey(key))
        {
            ports.put( key, "Port for etag: " + etags.get(key) );
        }
        return ports.get(key);
    }
    
    /**
     * Set port that correlates to etag.
     * @param key The key of the etag.
     * @return the port that was set.
     */
    public static String setPort( String key, String port )
    {
        ports.put( key, port );

        return port;
    }
}
