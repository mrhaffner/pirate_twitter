/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./src/**/*.{js,jsx,ts,tsx}'],
  theme: {
    extend: {
      width: {
        88: '22rem',
        152: '38rem',
      },
    },
  },
  plugins: [require('@tailwindcss/forms')],
};
