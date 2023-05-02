(function () {
  'use strict'

  // Selectors for the input fields we want to validate against.
  var forms = document.querySelectorAll('.needs-validation')
  var phoneNumber = document.querySelector('#phoneNumber');
  var email = document.querySelector('#email');
  var name = document.querySelector('#name');
  var message = document.querySelector('#message');
  var emailErrorMessage = document.querySelector('#email-validation-div');
  var phoneNumberErrorMessage = document.querySelector('#phoneNumber-validation-div');

  // Loop over the forms and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {

      form.addEventListener('submit', function (event) {
        var americanTelephoneRegexPattern = /^(\+?1\s?)?(\d{3}|\(\d{3}\))[\s\-]?\d{3}[\s\-]?\d{4}$/
        var emailRegexPattern = /^[a-z0-9\.-_]+[@][a-z0-9]+[\.][a-z0-9\.]{2,5}$/

        // Validating Phone Number Input against American Telephone number format
        if (!americanTelephoneRegexPattern.test(phoneNumber.value)) {
          phoneNumber.setCustomValidity('Please enter a valid US Phone Number');
        } else {
          phoneNumber.setCustomValidity('');
        }

        // Validating Email Input against Standard Email Formats
        if (!emailRegexPattern.test(email.value)) {
            email.setCustomValidity('Please enter a valid email');
        }  else {
            email.setCustomValidity('');
        }

        // Checking if any validation messages have been raise, either custom or default validation errors
        if (phoneNumber.validationMessage === ''
            && email.validationMessage === ''
            && name.validationMessage === ''
            && message.validationMessage === '') {
                name.validity.valid = true;
                email.validity.valid = true;
                phoneNumber.validity.valid = true;
                message.validity.valid = true;
        }

        if (!form.checkValidity()) {
          // Stops the form from being submitted
          event.preventDefault()
          event.stopPropagation()
          // Set the text Content for the phoneNumberErrorMessage div to the validation message
          // that was raised or else the text content is an empty string indicating no validation errors were raised
          if (phoneNumber.validationMessage !== '') {
            phoneNumberErrorMessage.textContent = phoneNumber.validationMessage;
          } else {
            phoneNumberErrorMessage.textContent = '';
          }

          // Set the text Content for the emailErrorMessage div to the validation message
          // that was raised or else the text content is an empty string indicating no validation errors were raised
          if (email.validationMessage !== '') {
            emailErrorMessage.textContent = email.validationMessage;
          } else {
            emailErrorMessage.textContent = '';
          }
        }

        form.classList.add('was-validated')
      }, false)
    })
})()