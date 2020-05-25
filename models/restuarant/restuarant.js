'use strict';

import mongoose from 'mongoose'

const Schema = mongoose.Schema;

const restuarantSchema = new Schema({
    Id: Number,
    Name: String,
    Address: String,
    Rating: Number,
    Latitude: Number,
    Longtitude: Number
});

restuarantSchema.index({ID:1});

const Restuarant = mongoose.model('Restuarant', restuarantSchema);

export default Restuarant;
