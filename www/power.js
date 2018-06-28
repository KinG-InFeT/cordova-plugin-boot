/**
 *
 * @Author Alben Yuan
 * @Date 2018-06-28 22:50
 */

cordova.define("cordova-plugin-boot.Power", function (require, exports, module) {


    var exec = require('cordova/exec');

    var Power = {
        shutdown: function () {
            exec(null, null, 'Power', 'shutdown');
        },
        reboot: function () {
            exec(null, null, 'Power', 'reboot');
        }
    };

    module.exports = Power;

});
