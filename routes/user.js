'use strict';

const express = require('express');
const UserController = require('../controller/User/UserController');

const router = express.Router();

router.get('/:uid', UserController.getUserInfoById);

module.exports = router;
