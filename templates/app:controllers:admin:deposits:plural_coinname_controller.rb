module Admin
  module Deposits
    class [{plural-coinname|capitalize}]Controller < ::Admin::Deposits::BaseController
      load_and_authorize_resource :class => '::Deposits::[{singular-coinname|capitalize}]'

      def index
        start_at = DateTime.now.ago(60 * 60 * 24 * 365)
        @[{plural-coinname}] = @[{plural-coinname}].includes(:member).
          where('created_at > ?', start_at).
          order('id DESC').page(params[:page]).per(20)
      end

      def update
        @[{singular-coinname}].accept! if @[{singular-coinname}].may_accept?
        redirect_to :back, notice: t('.notice')
      end
    end
  end
end
