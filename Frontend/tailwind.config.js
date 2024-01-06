/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './src/**/*.{js,jsx,ts,tsx}',
  ],
  theme: {
    extend: {},
    fontFamily: {
      mark: ["Simsun", "ui-sans-serif"]
    }
  },
  plugins: [],
  corePlugins: {
    preflight: false,
  }
}

