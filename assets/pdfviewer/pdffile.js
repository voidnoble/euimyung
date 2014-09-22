//var url = '../library/pdf_enterance_volume01.pdf';
// http://stackoverflow.com/questions/24291980/implementing-pdf-js-in-android-to-read-from-sd-card
var url = getURLParameter('file');

function getURLParameter(name) {
	return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search)||[,""])[1].replace(/\+/g, '%20'))||null
}