/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.data.dto

import java.io.Serializable


data class ReadingDTO(val from: String, val title: String, val content: String) : Serializable
