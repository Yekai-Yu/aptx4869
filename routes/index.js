'use strict';

const user = require('./user');

exports.router = (app) => {
    app.use('/user', user);
};
